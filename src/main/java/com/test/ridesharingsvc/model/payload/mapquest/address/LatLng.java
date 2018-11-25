
package com.test.ridesharingsvc.model.payload.mapquest.address;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class LatLng {

    private Double lat;
    private Double lng;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}
