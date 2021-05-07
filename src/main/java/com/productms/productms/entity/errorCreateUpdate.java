package com.productms.productms.entity;


import io.swagger.annotations.SwaggerDefinition;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@SwaggerDefinition
public class errorCreateUpdate {

    private String status_code;
    private String message;

    public errorCreateUpdate(String status_code, String message) {
        this.status_code = status_code;
        this.message = message;
    }

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
