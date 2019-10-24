layui.use(['form', 'table', 'element'], function () {
    let table = layui.table,
        form = layui.form,
        element = layui.element;

    element.render();
    let taskTable = table.render({
        elem: '#task-table',
        height: 'full',
        url:  "/job/list",
        // cellMinWidth: 90,
        // limit: 10,
        // size: 'lg',
        method: "post",
        parseData: function(res) {
            return{
                code: 0,
                "msg": "",
                "count": res.length,
                "data": res
            }
        },
        cols: [[
            {type:'checkbox'},
            {field: 'jobName', width: '10%', title: '任务名称'},
            {field: 'jobGroup', width: '10%', title: '任务分组'},
            {field: 'jobClassName', width: '20%', title: '任务类'},
            {field: 'cronExpression',  width: '10%',title: 'cron表达式'},
            {field: 'description', width: '20%', title: '任务描述'},
            {title: '任务状态', width: '10%', align: 'center', toolbar: '#jobStatusTpl'},
            {title: '操作',  align: 'center', toolbar: '#taskBar'}
        ]],
        page: true
    });
    let $ = layui.$, active = {
        reload: function () {
            let descSearch = $('#task-name-search');
            //执行重载
            table.reload('task-table', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }, where: {
                    description: descSearch.val()
                }
            });
        },
        batchRemove: function () {
            let descSearch = $('#task-name-search');
            let checkStatus = table.checkStatus('task-table');
            let data = checkStatus.data;
            if (data.length == 0) {
                layer.msg("请选择要删除的数据");
                return;
            }
            layer.confirm("确认要删除选中的【" + data.length + "】条数据吗?", function (index) {
                let ids = new Array();
                // 遍历所有选择的行数据，取每条数据对应的ID
                for (let i = 0; i < data.length; i++) {
                    ids[i] = data[i].id;
                }

                common.ajax(common.url.prefix + "/task/removeBatch", {ids: ids}, function (json) {
                    if (json.code === common.status.ok) {
                        layer.msg('删除成功！');
                        active.reload();
                    } else {
                        layer.msg("删除失败！" + json.message);
                    }
                });

                layer.close(index);
            });
        },
        insert: function () {
            layer.open({
                type: 1,
                title: '新增计划任务',
                area: ['455px', '410px'],
                // maxmin: true,   // 开启最大化最小化按钮
                content: $('#edit-task-window'),
                btn: ['确认','取消'],
                yes: function(index, obj) {
                    common.ajax('/job/add',$('#job-edit-from').serialize(),function (res) {
                        if(res.code != 200) {
                            layer.msg(res.message);
                        } else {
                            // obj.add();   这里可以考虑直接将添加成功的元素加进来
                            layer.msg("添加成功");
                            layer.close(index);
                        }
                    })
                },
                close: function(index, obj) {
                    $('#job-edit-from').reset()
                }
                // shadeClose: true, //点击遮罩关闭层
            });
        }
    };

    //监听单元格编辑
    table.on('edit(task)', function (obj) {
        let value = obj.value;
        let data = obj.data;
        common.ajax(common.url.prefix + "/task/edit", obj.data, function (json) {
            if (json.code === common.status.ok) {
                layer.msg('修改成功！<br/>' + '[ID: ' + data.id + '] 行字段更改为：' + value)
            } else {
                layer.msg("修改出错，错误信息：" + json.message);
            }
        })
    });

    $('#task-table-search').find('.layui-btn').on('click', function () {
        let type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    //监听工具条
    table.on('tool(task)', function (obj) {
        let data = obj.data;
        console.log(data);
        if (obj.event === 'run') {
            common.ajax("/job/trigger", {jobName: data.jobName, jobGroup: data.jobGroup}, function (json) {
                if (json.code === common.status.ok) {
                    layer.msg('运行成功！')
                } else {
                    layer.msg("运行失败！" + json.message);
                }
            });
        } else if (obj.event === 'del') {
            layer.confirm('确认删除任务【'+ data.jobName +'】吗？', function (index) {
                common.ajax("/job/delete", {jobName: data.jobName, jobGroup: data.jobGroup}, function (json) {
                    if (json.code === common.status.ok) {
                        obj.del();
                        layer.msg('删除成功！')
                    } else {
                        layer.msg("删除失败！" + json.message);
                    }
                });
                layer.close(index);
            });
        } else if (obj.event === 'checkout') {

        }
    });

    form.on('switch(jobStatus)', function (obj) {
        console.log(obj);
        console.log(this.value);
        console.log(this.name);
        console.log(obj.othis);
        let url = obj.elem.checked ? '/job/resume' : '/job/pause';
        common.ajax(url, {jobName: this.name, jobGroup: this.value}, function (json) {
            layer.tips('任务：' + ((obj.elem.checked) ? "开启" : "关闭"), obj.othis);
        });
    });

});