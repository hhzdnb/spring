package com.vesus.springbootdruid.service;



import com.vesus.springbootdruid.model.User;

import java.util.List;

public interface UserService {

    public List<User> findAll();

    public void saveUser(User book);

    public User findOne(long id);

    public void delete(long id);

    public List<User> findByName(String name);
}
