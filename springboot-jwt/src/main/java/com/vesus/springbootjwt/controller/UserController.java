package com.vesus.springbootjwt.controller;

import com.vesus.springbootjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    UserService userService ;

    @RequestMapping(value = "/user")
    public String getUser() throws Exception{

//        userService.findOne("");
        return "user appid " ;
    }

}
