package com.oliiyu.userservice.common.security;

import com.oliiyu.userservice.mapper.SysUserMapper;
import com.oliiyu.userservice.repository.entity.SysUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Author: oliiyu
 * Date: 2019/5/6 15:09
 * Description:
 * 第二个要实现的是 UserDetailsService ，这个接口只定义了一个方法 loadUserByUsername ，顾名思义，就是提供一种从用户名可以查到用户并返回的方法。
 * 注意，不一定是数据库，文本文件、xml文件等等都可能成为数据源，这也是为什么Spring提供这样一个接口的原因：保证你可以采用灵活的数据源。
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserEntity sysUser = sysUserMapper.findBySysUsername(username);

        if (sysUser == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return UserDetailsFactory.create(sysUser);
        }
    }
}
