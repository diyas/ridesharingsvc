
package com.test.ridesharingsvc.model.payload.mapquest;

import com.test.ridesharingsvc.model.payload.mapquest.latlon.Info;
import com.test.ridesharingsvc.model.payload.mapquest.latlon.Options;
import com.test.ridesharingsvc.model.payload.mapquest.latlon.Result;
import lombok.Data;

import java.util.List;

@Data
public class ResponseLatLng {

    private Info info;
    private Options options;
    private List<Result> results = null;


}
