package com.example.json.schema.enums;

public enum EnumResult {
    SUCESS(0, "ok"),
    ERROR(-1, "error"),
    DEVICE_ID_MISS(-1, "device_id miss"),
    DEVICE_IS_REGISTERED(-1, "device is registered"),
    DEVICE_IS_UNREGISTER(-1, "device is unregister"),
    DEVICE_ID_ERROR_OR_TIMEOUT(-1, "device_id error or timeout"),
    SERVER_ERROR(-1, "device server error"),
    DB_WRITES_ERROR(-1, "db writes error"),
    PARAMS_ERROR(-1, "params error"),
    PUSH_NOT_BIND(-1, "device is not bind push")
    ;
    private Integer status;
    private String message;

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

    private EnumResult(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
