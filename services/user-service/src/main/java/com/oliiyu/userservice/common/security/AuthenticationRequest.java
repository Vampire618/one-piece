package com.oliiyu.userservice.common.security;

import java.io.Serializable;

/**
 * Author: oliiyu
 * Date: 2019/5/6 15:44
 * Description:
 */
public class AuthenticationRequest implements Serializable {

    private String username;
    private String password;

    public AuthenticationRequest() {
        super();
    }

    public AuthenticationRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
