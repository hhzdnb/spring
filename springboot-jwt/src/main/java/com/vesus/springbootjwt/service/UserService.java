package com.vesus.springbootjwt.service;

import com.vesus.springbootjwt.model.UserInfo;
import java.util.List;

public interface UserService {

    public void saveUser(UserInfo userInfo);

    public UserInfo findOne(String appid);

    public void delete(long id);

    public List<UserInfo> findByName(String name);
}
