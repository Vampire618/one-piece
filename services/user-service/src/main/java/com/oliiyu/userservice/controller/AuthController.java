package com.oliiyu.userservice.controller;

import com.oliiyu.userservice.common.CommonResult;
import com.oliiyu.userservice.common.security.AuthenticationRequest;
import com.oliiyu.userservice.common.security.AuthenticationResponse;
import com.oliiyu.userservice.common.security.JwtConst;
import com.oliiyu.userservice.repository.entity.SysUserEntity;
import com.oliiyu.userservice.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Author: Oliiyu
 * Date: 2019/5/5 15:47
 * Description:
 */
@Api("权限验证类")
@RestController
public class AuthController {
//    @Value("${jwt.header}")
//    private String tokenHeader;

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody AuthenticationRequest authenticationRequest) throws AuthenticationException {
        final String token = authService.login(authenticationRequest.getEmail(), authenticationRequest.getPassword());

        // Return the token
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(
            HttpServletRequest request) throws AuthenticationException {
        String token = request.getHeader(JwtConst.HEADER_STRING);
        String refreshedToken = authService.refresh(token);
        if (refreshedToken == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(new AuthenticationResponse(refreshedToken));
        }
    }

    @ApiOperation("用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CommonResult register(@RequestBody @Validated SysUserEntity addedUser) throws AuthenticationException {
        SysUserEntity user = authService.register(addedUser);
        return CommonResult.success(user);
    }
}
