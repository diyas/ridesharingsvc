
package com.test.ridesharingsvc.model.payload.mapquest.address;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class LatLng__ {

    private Double longitude;
    private Double latitude;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
