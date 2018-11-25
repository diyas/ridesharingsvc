
package com.test.ridesharingsvc.model.payload.mapquest.address;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Copyright {

    private String text;
    private String imageUrl;
    private String imageAltText;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}
