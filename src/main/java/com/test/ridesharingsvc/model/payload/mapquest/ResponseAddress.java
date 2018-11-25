
package com.test.ridesharingsvc.model.payload.mapquest;

import com.test.ridesharingsvc.model.payload.mapquest.address.Info;
import com.test.ridesharingsvc.model.payload.mapquest.address.Options;
import com.test.ridesharingsvc.model.payload.mapquest.address.Result;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Data
public class ResponseAddress {

    private Info info;
    private Options options;
    private List<Result> results = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}
