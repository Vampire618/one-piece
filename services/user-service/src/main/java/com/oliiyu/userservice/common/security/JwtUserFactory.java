package com.oliiyu.userservice.common.security;

import com.oliiyu.userservice.repository.entity.SysUserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: oliiyu
 * Date: 2019/5/5 15:43
 * Description:
 */
public class JwtUserFactory {

    private JwtUserFactory() {
    }


    public static JwtUser create(SysUserEntity user) {
        ArrayList<String> list = new ArrayList<String>();
        list.add("USER");
        list.add("ADMIN");
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                mapToGrantedAuthorities(list)
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

}
