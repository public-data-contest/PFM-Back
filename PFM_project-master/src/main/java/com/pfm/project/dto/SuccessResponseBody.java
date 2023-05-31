package com.pfm.project.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;

public class SuccessResponseBody<T> {
    private HttpStatus status = HttpStatus.OK;
    private String message = "Success";
    private T data  = null;

    @Builder
    public SuccessResponseBody(HttpStatus status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }
}
