package com.vesus.springtomcat.controller;

import com.vesus.springtomcat.model.User;
import com.vesus.springtomcat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService ;

    @RequestMapping(value = "/userlist")
    public List<User> getUserList(HttpServletRequest req, HttpServletResponse resp){
        System.out.println(req.getSession().getServletContext().getRealPath("/"));

        return userService.findAll() ;
    }
}
