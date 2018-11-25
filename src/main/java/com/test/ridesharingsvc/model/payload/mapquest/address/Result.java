
package com.test.ridesharingsvc.model.payload.mapquest.address;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Result {

    private ProvidedLocation providedLocation;
    private List<Location> locations = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}
