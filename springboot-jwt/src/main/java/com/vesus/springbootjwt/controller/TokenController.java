package com.vesus.springbootjwt.controller;

import com.vesus.springbootjwt.model.TokenInfo;
import com.vesus.springbootjwt.model.TokenResult;
import com.vesus.springbootjwt.model.UserInfo;
import com.vesus.springbootjwt.service.TokeninfoService;
import com.vesus.springbootjwt.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/api")
public class TokenController {

    @Autowired
    private TokeninfoService tokeninfoService ;

    @Autowired
    private UserService userService ;

    /**
     * 获取token，更新token
     * @param appId 用户编号
     * @param appSecret 用户密码
     * @return
     */
    @RequestMapping(value = "/token" ,method = {RequestMethod.POST,RequestMethod.GET})
    public TokenResult token(@RequestParam String appId , @RequestParam String appSecret){
        TokenResult token = new TokenResult();
        //判断appid是否为空
        if(appId == null || appId.trim() == "")
        {
            token.setFlag(false);
            token.setMsg("appId is not found!");
        }
        //判断appSecret是否为空
        else if(appSecret == null || appSecret.trim() == "")
        {
            token.setFlag(false);
            token.setMsg("appSecret is not found!");
        }else{
            UserInfo userInfo = userService.findOne(appId);
            //如果用户不存在
            if (userInfo == null)
            {
                token.setFlag(false);
                token.setMsg("appId : " + appId + ", 缺失！");
            }//验证appSecret是否存在
            else if (!new String(userInfo.getAppSecret()).equals(appSecret.replace(" ","+")))
            {
                token.setFlag(false);
                token.setMsg("appSecret无效！");
            }
            else
            {
                TokenInfo tokenInfo = tokeninfoService.findOne(appId);
                //返回token值
                String tokenStr = null;
                if (tokenInfo==null){
                    //生成Token
                    tokenStr = createNewToken(appId) ;
                    //将token保持到数据库
                    tokenInfo = new TokenInfo();
                    tokenInfo.setAppId(userInfo.getAppId());
                    tokenInfo.setBuildTime(String.valueOf(System.currentTimeMillis()));
                    tokenInfo.setToken(tokenStr.getBytes());
                    tokeninfoService.saveToken(tokenInfo);

                }
                else
                {
                    //判断数据库中token是否过期，如果没有过期不需要更新直接返回数据库中的token即可
                    //数据库中生成时间
                    long dbBuildTime = Long.valueOf(tokenInfo.getBuildTime());
                    //当前时间
                    long currentTime = System.currentTimeMillis();
                    //如果当前时间 - 数据库中生成时间 < 7200 证明可以正常使用
                    long second = TimeUnit.MILLISECONDS.toSeconds(currentTime - dbBuildTime);
                    if (second > 0 && second < 7200) {
                        tokenStr = new String(tokenInfo.getToken());
                    }
                    //超时
                    else{
                        //生成newToken
                        tokenStr = createNewToken(appId);
                        //更新token
                        tokenInfo.setToken(tokenStr.getBytes());
                        //更新生成时间
                        tokenInfo.setBuildTime(String.valueOf(System.currentTimeMillis()));
                        //执行更新
                        tokeninfoService.saveToken(tokenInfo);
                    }
                }
                //设置返回token
                token.setToken(tokenStr);
            }
        }
        return token;
    }

    /**
     * 创建新token
     * @param appId
     * @return
     */
    private String createNewToken(String appId){
        //获取当前时间
        Date now = new Date(System.currentTimeMillis());
        //过期时间
        Date expiration = new Date(now.getTime()+7200000);
        return Jwts.builder().setSubject(appId)
                .setIssuedAt(now).setIssuer("Online YAuth Builder")
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256,"Authv1.0.0")
                .compact();
    }
}
