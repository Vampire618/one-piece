package com.oliiyu.userservice.common.security;

import java.io.Serializable;

/**
 * Author: oliiyu
 * Date: 2019/5/6 15:44
 * Description:
 */
public class AuthenticationRequest implements Serializable {

    private String email;
    private String username;
    private String password;
    private String phoneNumber;

    public AuthenticationRequest() {
        super();
    }

    public AuthenticationRequest(String username, String password,
                                 String email, String phoneNumber) {
        this.setUsername(username);
        this.setPassword(password);
        this.setPassword(email);
        this.setPassword(phoneNumber);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
