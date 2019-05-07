package com.oliiyu.userservice.controller;

import com.google.gson.Gson;
import com.oliiyu.userservice.common.utils.GeneralMessage;
import com.oliiyu.userservice.repository.entity.SysUserEntity;
import com.oliiyu.userservice.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping(value = "/demo")
public class HelloWorldController {

    @Autowired
    private SysUserService sysUserService;


    @RequestMapping(value = "/hello")
    public String helloWorld(@RequestParam String name) {
        return "" + name;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public GeneralMessage addUser(@RequestBody SysUserEntity userEntity) {
//        SysUserEntity userEntity = new Gson().fromJson(params, SysUserEntity.class);
        userEntity.setId(UUID.randomUUID() + "");
        userEntity.setGmtCreate(new Date(System.currentTimeMillis()));
        Integer insertNum = sysUserService.addSysUser(userEntity);
        return insertNum > 0 ? new GeneralMessage(0, "操作成功！", insertNum)
                : new GeneralMessage(1, "操作失败！", null);
    }


}
