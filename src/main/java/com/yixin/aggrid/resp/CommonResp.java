package com.yixin.aggrid.resp;

public class CommonResp<T> {

    /**
     * whether success
     */
    private boolean success = true;

    /**
     * message
     */
    private String message;

    /**
     * response body <T>
     */
    private T content;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

