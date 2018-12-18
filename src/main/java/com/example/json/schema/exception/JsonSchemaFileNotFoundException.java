package com.example.json.schema.exception;

/**
 * json schema文件未找到异常
 *
 * @author: huangwh
 * @mail huangwh@txtws.com
 * @date: 2018-11-21 18:04
 */
public class JsonSchemaFileNotFoundException extends Exception {
    public JsonSchemaFileNotFoundException() {
        super();
    }

    public JsonSchemaFileNotFoundException(String message) {
        super(message);
    }

    // 用指定的详细信息和原因构造一个新的异常
    public JsonSchemaFileNotFoundException(String message, Throwable cause){
        super(message,cause);
    }

    //用指定原因构造一个新的异常
    public JsonSchemaFileNotFoundException(Throwable cause) {
        super(cause);
    }
}
