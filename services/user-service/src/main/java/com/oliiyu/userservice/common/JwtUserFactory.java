package com.oliiyu.userservice.common;

import com.oliiyu.userservice.repository.entity.SysUserEntity;

/**
 * @Auther: oliiyu
 * @Date: 2019/5/5 15:43
 * @Description:
 */
public final class JwtUserFactory {

    private JwtUserFactory() {
    }

//    public static JwtUser create(SysUserEntity user) {
//        return new JwtUser(
//                user.getId(),
//                user.getUserName(),
//                user.getPassword()
//        );
//    }
//
//    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
//        return authorities.stream()
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//    }
}
