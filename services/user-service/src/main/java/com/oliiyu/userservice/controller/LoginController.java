package com.oliiyu.userservice.controller;

import com.oliiyu.userservice.common.CommonResult;
import com.oliiyu.userservice.common.security.AuthenticationRequest;
import com.oliiyu.userservice.service.AuthService;
import com.oliiyu.userservice.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private AuthService authService;


    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public CommonResult getAccounts() {
        return CommonResult.success(sysUserService.getAllSysUser());
    }

    @RequestMapping(value = "/accounts2", method = RequestMethod.GET)
    public ResponseEntity<?> getAccounts2() {
        return ResponseEntity.ok(sysUserService.getAllSysUser());
    }

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public CommonResult login(@RequestBody AuthenticationRequest authenticationRequest)
            throws AuthenticationException {
        final String token = authService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        return CommonResult.success(token);
    }


}
