
package com.test.ridesharingsvc.model.payload.mapquest.latlon;

import lombok.Data;

import java.util.List;

@Data
public class Result {

    private ProvidedLocation providedLocation;
    private List<Location> locations = null;
}
