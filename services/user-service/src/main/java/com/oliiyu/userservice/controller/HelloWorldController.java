package com.oliiyu.userservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/demo")
public class HelloWorldController {

    @RequestMapping(value = "/hello")
    public String helloWorld(@RequestParam String name){
        return "" + name;
    }
}
