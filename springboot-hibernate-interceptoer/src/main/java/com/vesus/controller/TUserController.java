package com.vesus.controller;


import com.vesus.domain.TUser;
import com.vesus.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tUserController")
public class TUserController {

    @Autowired
    private TUserService tUserService;

    @RequestMapping("/save")
    public TUser save(TUser tUser) {
        return tUserService.save(tUser);
    }

    @RequestMapping("/findOneById")
    public TUser findOneById(Integer id) {
        return tUserService.findOne(id);
    }

    @RequestMapping("/findOptionalOneById")
    public Optional<TUser> findOptionOneById(Integer id) {
        return tUserService.findById(id);
    }

    @RequestMapping("/existsById")
    public boolean existsById(Integer id) {
        return tUserService.existsById(id);
    }

    @RequestMapping("/findAll")
    public List<TUser> findAll() {
        return tUserService.findAll();
    }

    @RequestMapping("/findAllByEntityManager")
    public List<TUser> findAllByEntityManager() {
        return tUserService.findAllByEntityManager("select * from t_user", TUser.class);
    }

    @RequestMapping("/count")
    public Long count() {
        return tUserService.count();
    }

    @RequestMapping("/deleteById")
    public void deleteById(Integer id) {
        tUserService.deleteById(id);
    }

    @RequestMapping("/deleteByEntity")
    public void delete(TUser tUser) {
        tUserService.delete(tUser);
    }
}
