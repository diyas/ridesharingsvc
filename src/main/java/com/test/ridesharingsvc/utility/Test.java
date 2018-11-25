package com.test.ridesharingsvc.utility;

import org.springframework.web.util.UriComponentsBuilder;


public class Test {
    public static void main(String args[]) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(MapQuestAPI.ADDRESS_TO_LATLNG)
                // Add query parameter
                .queryParam("key", MapQuestAPI.API_KEY)
                .queryParam("location", "Washington,DC");
        //ResponseLatLng root = (ResponseLatLng) Utility.callApi(builder);
        //System.out.println(root.toString());
    }
}
