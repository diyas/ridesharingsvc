package com.test.ridesharingsvc.services;

import com.test.ridesharingsvc.model.payload.mapquest.ResponseAddress;
import com.test.ridesharingsvc.model.payload.mapquest.ResponseLatLng;

public interface MapQuestService {
    ResponseLatLng getLatLon(String address);
    ResponseAddress getAddress(String lat, String lon);
}
