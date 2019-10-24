package com.alex.job.web.api;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * 用于包装返回值
 */
public class RestResult implements Serializable {

    private int code;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public RestResult(int code, String message, Object result) {
        super();
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public RestResult(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public RestResult() {
        super();
        this.code = 200;
    }

    public RestResult(int code) {
        super();
        this.code = code;
    }

    public RestResult(Object result) {
        super();
        this.code = 200;
        this.result = result;
    }
}
