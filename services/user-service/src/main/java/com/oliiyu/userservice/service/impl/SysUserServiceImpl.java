package com.oliiyu.userservice.service.impl;

import com.oliiyu.userservice.mapper.SysUserMapper;
import com.oliiyu.userservice.repository.entity.SysUserEntity;
import com.oliiyu.userservice.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysUserEntity> getAllSysUser() {
        return sysUserMapper.getAllSysUser();
    }
}
