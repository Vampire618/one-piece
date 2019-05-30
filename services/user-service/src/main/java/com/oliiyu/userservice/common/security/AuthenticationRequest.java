package com.oliiyu.userservice.common.security;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Author: oliiyu
 * Date: 2019/5/6 15:44
 * Description:
 */
public class AuthenticationRequest implements Serializable {

    @Email(message = "邮件格式错误")
    private String email;

    @Pattern(regexp = "^[A-Za-z0-9_]{8,64}$", message = "用户名格式错误")
    private String username;

    @Pattern(regexp = "^(?![A-Z]+$)(?![a-z]+$)(?!\\d+$)(?![\\W_]+$)\\S{6,16}$", message = "密码格式错误")
    private String password;

    @Pattern(regexp = "^1(3|4|5|7|8)\\\\d{9}$", message = "手机号码格式错误")
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
