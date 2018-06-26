package com.vesus.springboothistory.controller;

import com.vesus.springboothistory.model.User;
import com.vesus.springboothistory.service.UserService;
import com.vesus.springboothistory.utils.BusinessChangeUtil;
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

    @RequestMapping(value = "save")
    public String saveLog(){
       User user = new User() ;
       user.setId(1);
       user.setName("李坤");

        User user2 = new User() ;
        user2.setId(1);
        user2.setName("李四");

        BusinessChangeUtil<User> businessChangeUtil = new BusinessChangeUtil<User>();

        businessChangeUtil.record(User.class,user,user2,1,"vesus");

       return "sucess" ;
    }
}
