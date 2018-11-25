
package com.test.ridesharingsvc.model.payload.mapquest.address;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Options {

    private Integer maxResults;
    private Boolean thumbMaps;
    private Boolean ignoreLatLngInput;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
