package com.vesus.springbootjwt.service;

import com.vesus.springbootjwt.model.TokenInfo;
import com.vesus.springbootjwt.model.UserInfo;

import java.util.List;

public interface TokeninfoService {

    public void saveToken(TokenInfo tokenInfo);

    public TokenInfo findOne(String appid);

    public void delete(long id);

    public List<TokenInfo> findByName(String name);
}
