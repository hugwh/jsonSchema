package com.example.json.schema.exception;

/**
 * 参数校验异常
 *
 * @author: huangwh
 * @mail huangwh@txtws.com
 * @date: 2018-11-21 18:04
 */
public class ParamException extends Exception {
    public ParamException() {
        super();
    }

    public ParamException(String message) {
        super(message);
    }

    // 用指定的详细信息和原因构造一个新的异常
    public ParamException(String message, Throwable cause){
        super(message,cause);
    }

    //用指定原因构造一个新的异常
    public ParamException(Throwable cause) {
        super(cause);
    }
}
