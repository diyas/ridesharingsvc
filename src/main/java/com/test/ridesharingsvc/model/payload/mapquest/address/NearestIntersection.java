
package com.test.ridesharingsvc.model.payload.mapquest.address;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class NearestIntersection {

    private String streetDisplayName;
    private String distanceMeters;
    private LatLng__ latLng;
    private String label;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


}
