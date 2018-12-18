package com.example.json.schema.entity;

import com.alibaba.fastjson.JSON;
import com.example.json.schema.enums.EnumResult;

import java.io.Serializable;

/**
 * 接口返回结果实体
 *
 * @author: huangwh
 * @mail huangwh@txtws.com
 * @date: 2018-11-13 10:11
 */
public class Result<T> implements Serializable {

    private Integer status;
    private String message;
    private T data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static String toString(Result result) {
        return JSON.toJSON(result).toString();
    }

    public Result() {}

    public Result(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Result(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Result successResult(T data) {
        this.status = EnumResult.SUCESS.getStatus();
        this.message = EnumResult.SUCESS.getMessage();
        this.data = data;

        return this;
    }

    public Result successResult() {
        this.status = EnumResult.SUCESS.getStatus();
        this.message = EnumResult.SUCESS.getMessage();

        return this;
    }
}
