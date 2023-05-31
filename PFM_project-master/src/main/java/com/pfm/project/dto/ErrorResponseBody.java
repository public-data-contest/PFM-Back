package com.pfm.project.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorResponseBody {
    private LocalDateTime timeStamp = LocalDateTime.now();
    private Integer status = HttpStatus.BAD_REQUEST.value();
    private String error = null;
    private String code = HttpStatus.BAD_REQUEST.name();
    private String message = "Failed to process request";


    @Builder
    public ErrorResponseBody(Integer status, String error, String code, String message) {
        this.status = status == null ? this.status : status;
        this.error = error == null ? this.error : error;
        this.code = code == null ? this.code : code;
        this.message = message == null ? this.message : message;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ErrorResponseBody{" +
                "timeStamp=" + timeStamp +
                ", status=" + status +
                ", error='" + error + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
