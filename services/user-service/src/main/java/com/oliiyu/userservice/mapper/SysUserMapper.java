package com.oliiyu.userservice.mapper;

import com.oliiyu.userservice.repository.entity.SysUserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserMapper {

    List<SysUserEntity> getAllSysUser();

    SysUserEntity findBySysUsername(String username);

    SysUserEntity addSysUser(SysUserEntity sysUserEntity);
}
