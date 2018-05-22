package com.vesus.springbootjwt.service.impl;

import com.vesus.springbootjwt.model.UserInfo;
import com.vesus.springbootjwt.repository.UserInfoRepository;
import com.vesus.springbootjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserInfoRepository userInfoRepository ;

    public void saveUser(UserInfo user) {

    }

    public UserInfo findOne(String appId) {

        return userInfoRepository.findOne(new Specification<UserInfo>() {
            @Override
            public Predicate toPredicate(Root<UserInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                criteriaQuery.where(criteriaBuilder.equal(root.get("appId"),appId)) ;
                return null;
            }
        });
    }

    public void delete(long id) {

    }

    public List<UserInfo> findByName(String name) {
        return null;
    }
}
