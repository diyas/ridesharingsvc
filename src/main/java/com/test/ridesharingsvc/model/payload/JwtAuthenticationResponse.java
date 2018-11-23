package com.test.ridesharingsvc.model.payload;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {

	private String accessToken;
    private String tokenType = "DCK";

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }

}
