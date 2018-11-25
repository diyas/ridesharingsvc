package com.test.ridesharingsvc.services;

import com.test.ridesharingsvc.model.payload.mapquest.ResponseAddress;
import com.test.ridesharingsvc.model.payload.mapquest.ResponseLatLng;
import com.test.ridesharingsvc.utility.MapQuestAPI;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class MapQuestServiceImpl implements MapQuestService{

    public ResponseLatLng getLatLon(String address){
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(MapQuestAPI.ADDRESS_TO_LATLNG)
                .queryParam("key", MapQuestAPI.API_KEY)
                .queryParam("location", address);
        ResponseLatLng root = restTemplate.getForObject(builder.toUriString(), ResponseLatLng.class);
        return root;
    }

    public ResponseAddress getAddress(String lat, String lon){
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(MapQuestAPI.LATLNG_TO_ADDRESS)
                .queryParam("key", MapQuestAPI.API_KEY)
                .queryParam("location", lat +","+lon);
        ResponseAddress root = restTemplate.getForObject(builder.toUriString(), ResponseAddress.class);
        return root;
    }
}
