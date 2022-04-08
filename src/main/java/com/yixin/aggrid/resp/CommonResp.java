package com.yixin.aggrid.resp;

public class CommonResp<T> {

    /**
     * status code
     */
    private Integer statusCode;

    /**
     * message
     */
    private String message;

    /**
     * response body <T>
     */
    private T content;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}

