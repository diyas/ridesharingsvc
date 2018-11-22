package com.test.ridesharingsvc.model.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class Response {
    private int code;
    private String message;
    private Object data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Paginate paginate;
}
