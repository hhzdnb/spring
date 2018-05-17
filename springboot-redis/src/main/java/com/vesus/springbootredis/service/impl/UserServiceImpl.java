package com.vesus.springbootredis.service.impl;

import com.vesus.springbootredis.model.User;
import com.vesus.springbootredis.repository.UserRepository;
import com.vesus.springbootredis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository ;

    //@Cacheable,如果没有指定key则方法参数作为key保存到缓存中。
    @Cacheable(value = "users")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    //@CachePut缓存新增的或更新的数据到缓存,其中缓存名字是 user 。数据的key是user的id
    @CachePut(value = "user",key = "#user.id")
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Cacheable(value = "user",key = "#id")
    public User findOne(long id) {
        User user =  userRepository.findOne(id);
        return user ;
    }

    //@CacheEvict 从缓存user中删除key为id 的数据
    @CacheEvict(value = "user",key = "#id")
    public void delete(long id) {

    }

    @Override
    public List<User> findByName(String name) {
        return null;
    }
}
