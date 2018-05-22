package com.vesus.springbootjwt.service.impl;

import com.vesus.springbootjwt.model.TokenInfo;
import com.vesus.springbootjwt.model.UserInfo;
import com.vesus.springbootjwt.repository.TokenRepository;
import com.vesus.springbootjwt.repository.UserInfoRepository;
import com.vesus.springbootjwt.service.TokeninfoService;
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
public class TokeninfoServiceImpl implements TokeninfoService {

    @Autowired
    TokenRepository tokenRepository ;

    public void saveToken(TokenInfo tokenInfo) {
        tokenRepository.save(tokenInfo);
    }

    public TokenInfo findOne(String appId) {
        return tokenRepository.findOne(new Specification<TokenInfo>() {
            @Override
            public Predicate toPredicate(Root<TokenInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                criteriaQuery.where(criteriaBuilder.equal(root.get("appId"),appId));
                return null;
            }
        });
    }

    public void delete(long id) {

    }

    public List<TokenInfo> findByName(String name) {
        return null;
    }
}
