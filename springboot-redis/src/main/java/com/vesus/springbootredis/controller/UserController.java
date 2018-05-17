package com.vesus.springbootredis.controller;

import com.vesus.springbootredis.model.User;
import com.vesus.springbootredis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService ;

    @RequestMapping(value = "/userlist")
    public List<User> getUserList(){

        return userService.findAll() ;
    }

    @RequestMapping(value = "/save")
    public void saveUser(){
        User user = new User() ;
        user.setId(2);
        user.setName("user2");
        user.setAge(22);
        userService.saveUser(user);
    }

    @RequestMapping(value = "/findbyid")
    public User findById(){

        return userService.findOne(new Long(1));
    }

    @RequestMapping(value = "/delid")
    public void delid(){

        userService.delete(new Long(1));
    }
}
