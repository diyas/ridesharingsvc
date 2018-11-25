
package com.test.ridesharingsvc.model.payload.mapquest.latlon;

import lombok.Data;

import java.util.List;

@Data
public class Info {

    private Integer statuscode;
    private Copyright copyright;
    private List<Object> messages = null;


}
