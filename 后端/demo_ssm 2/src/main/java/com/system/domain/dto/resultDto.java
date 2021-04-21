package com.system.domain.dto;

/**
 * 返回前端的信息类
 * @param <T>
 */
public class resultDto<T>{
    private Boolean result;
    private String message;
    private T data;

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
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
}
