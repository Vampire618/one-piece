package com.oliiyu.userservice.service;

import com.oliiyu.userservice.repository.entity.SysUserEntity;

/**
 * @Auther: oliiyu
 * @Date: 2019/5/5 15:51
 * @Description:
 */
public interface AuthService {
    SysUserEntity register(SysUserEntity userToAdd);

    String login(String username, String password);

    String refresh(String oldToken);
}
