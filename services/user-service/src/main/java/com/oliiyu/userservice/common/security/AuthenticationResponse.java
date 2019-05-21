package com.oliiyu.userservice.common.security;

import java.io.Serializable;

/**
 * Author: oliiyu
 * Date: 2019/5/6 15:44
 * Description:
 */
public class AuthenticationResponse implements Serializable {

    private final String token;

    public AuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}
