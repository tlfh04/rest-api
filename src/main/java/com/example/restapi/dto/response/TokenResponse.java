package com.example.restapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class TokenResponse {

    private String accessToken;
    private String tokenType;

    public static TokenResponse of(String accessToken){
        return new TokenResponse(accessToken,"Bearer");
    }

}
