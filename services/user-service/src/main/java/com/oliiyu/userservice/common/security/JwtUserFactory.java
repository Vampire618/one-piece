package com.oliiyu.userservice.common.security;

import com.oliiyu.userservice.repository.entity.SysUserEntity;

/**
 * Author: oliiyu
 * Date: 2019/5/5 15:43
 * Description:
 */
public class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(SysUserEntity user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword()
        );
    }

}
