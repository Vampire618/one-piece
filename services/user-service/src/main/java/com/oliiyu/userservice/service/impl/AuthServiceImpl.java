package com.oliiyu.userservice.service.impl;

import com.oliiyu.userservice.common.JwtUser;
import com.oliiyu.userservice.mapper.SysUserMapper;
import com.oliiyu.userservice.repository.entity.SysUserEntity;
import com.oliiyu.userservice.service.AuthService;
import com.oliiyu.userservice.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Auther: oliiyu
 * @Date: 2019/5/5 15:53
 * @Description: 1. 登录时要生成token，完成Spring Security认证，然后返回token给客户端
 * 2. 注册时将用户密码用BCrypt加密，写入用户角色，由于是开放注册，所以写入角色系统控制，将其写成 ROLE_USER
 * 3. 提供一个可以刷新token的接口 refresh 用于取得新的token
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Value("${jwt.tokenHead}")
    private String tokenHead;


    /**
     * 注册
     * @param sysUserToAdd
     * @return
     */
    @Override
    public SysUserEntity register(SysUserEntity sysUserToAdd) {
        final String username = sysUserToAdd.getUserName();
        if (sysUserMapper.findBySysUsername(username) != null) {
            return null;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = sysUserToAdd.getPassword();
        sysUserToAdd.setPassword(encoder.encode(rawPassword));
//        userToAdd.setLastPasswordResetDate(new Date());
//        userToAdd.setRoles(asList("ROLE_USER"));
        return sysUserMapper.addSysUser(sysUserToAdd);
    }

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }

    /**
     * 刷新
     * @param oldToken
     * @return
     */
    @Override
    public String refresh(String oldToken) {
        final String token = oldToken.substring(tokenHead.length());
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
//        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
//            return jwtTokenUtil.refreshToken(token);
//        }
        return null;
    }
}
