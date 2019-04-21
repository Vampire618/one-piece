package com.ziyu.oaservice.controller;

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
