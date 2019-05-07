package com.oliiyu.userservice.service;

import com.oliiyu.userservice.repository.entity.SysUserEntity;

import java.util.List;

public interface SysUserService {
    List<SysUserEntity> getAllSysUser();

    Integer addSysUser(SysUserEntity sysUserEntity);
}
