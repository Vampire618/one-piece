package com.oliiyu.userservice.controller;

import com.oliiyu.userservice.mapper.SysUserMapper;
import com.oliiyu.userservice.repository.entity.SysUserEntity;
import com.oliiyu.userservice.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserMapper sysUserMapper;

    @RequestMapping(value = "/do", method = RequestMethod.GET)
    public List<SysUserEntity> systemLogin() {
        return sysUserService.getAllSysUser();
    }

//    @RequestMapping(value = "login.do", method = RequestMethod.POST)
//    public ServerResponse<User> login(String username, String password, HttpSession session) {
//        ServerResponse<User> response = iUserService.login(username, password);
//        if (response.isSuccess()) {
//            session.setAttribute(Const.CURRENT_USER, response.getData());
//        }
//        return response;
//    }

}
