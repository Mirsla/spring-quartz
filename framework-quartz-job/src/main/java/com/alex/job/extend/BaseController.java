package com.alex.job.extend;


import com.alex.job.web.api.RestResult;

/**
 * 所有controller的继承类，提取常用的公用方法到这里
 */
public class BaseController {

    /**
     * 包装操作结果，code值表明本次操作的执行状态
     *
     * @author alex
     * @param result 返回结果，object
     * @param code  返回的code
     * @param msg   返回的消息
     * @return  RestResult
     */
    protected Object wrapResult(Object result, int code, String msg) {
        RestResult r = new RestResult();
        r.setResult(result);
        r.setCode(code);
        r.setMessage(msg);
        return r;
    }

    /**
     *  包装操作结果，code值为200
     *
     * @param result 返回结果 object
     * @return RestResult
     */
    protected Object wrapResult(Object result) {
        RestResult r = new RestResult(result);
        return r;
    }

    /**
     *  包装操作结果
     *      无返回结果
     *
     * @param code 执行结果
     * @param msg 返回消息
     * @return RestResult
     */
    protected Object wrapResult(int code, String msg) {
        RestResult r = new RestResult(code, msg);
        return r;
    }

    /**
     *  包装操作结果，code值为200
     *
     * @return RestResult
     */
    protected Object wrapResult() {
        RestResult r = new RestResult(200);
        return r;
    }
}
