
package com.test.ridesharingsvc.model.payload.mapquest.address;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class RoadMetadata {

    private String speedLimitUnits;
    private Object tollRoad;
    private Integer speedLimit;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}
