package com.test.ridesharingsvc.model.payload;

import lombok.Data;

import java.util.Map;

@Data
public class ErrorJson {

    private Integer status;
    private String error;
    private String message;
    private String timeStamp;
    private String trace;

    public ErrorJson(int status, Map<String, Object> errorAttributes) {
        this.status = status;
        this.error = (String) errorAttributes.get("error");
        this.message = (String) errorAttributes.get("message");
        this.timeStamp = errorAttributes.get("timestamp").toString();
        this.trace = (String) errorAttributes.get("trace");
    }
}
