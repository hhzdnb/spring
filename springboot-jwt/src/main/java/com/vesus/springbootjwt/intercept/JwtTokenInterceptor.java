package com.vesus.springbootjwt.intercept;

import com.vesus.springbootjwt.model.TokenInfo;
import com.vesus.springbootjwt.service.TokeninfoService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.security.SignatureException;

public class JwtTokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //自动排除生成token的路径,并且如果是options请求是cors跨域预请求，设置allow对应头信息
        if(request.getRequestURI().equals("/api/token")||RequestMethod.OPTIONS.toString().equals(request.getMethod())){
            return true ;
        }

        //获取头信息
        final String authHeader = request.getHeader("X-YAuth-Token") ;

        try {

            //如果取出的token信息为空
            if (authHeader==null||authHeader.trim()==""){
                throw new SignatureException("无法获取X-YAuth-Token！");
            }

            //获取jwt实体对象接口实例
            final Claims claims = Jwts.parser().setSigningKey("Authv1.0.0").parseClaimsJws(authHeader).getBody();

            //从数据库中获取token
            TokenInfo token = getBean(TokeninfoService.class,request).findOne(claims.getSubject());
            String tokenval = new String(token.getToken());

            if (tokenval==null||tokenval.trim()==""){
                throw new SignatureException("无法获取token信息，请重新获取！");
            }

            //token是否与客户端传来的一致
            if(!tokenval.equals(authHeader)){
                throw new SignatureException("无法获取token信息，请重新获取！");
            }
        }catch (SignatureException | ExpiredJwtException e){
            //输出对象
            PrintWriter writer = response.getWriter();

            //输出error消息
            writer.write("需要输入token");
            writer.close();
            return false;
        }
        //出现异常时
        catch (final Exception e)
        {
            //输出对象
            PrintWriter writer = response.getWriter();
            //输出error消息
            writer.write(e.getMessage());
            writer.close();
            return false;
        }
        return true;
    }

    /**
     * 根据传入的类型获取spring管理的对应bean
     * @param clazz 类型
     * @param request 请求对象
     * @param <T>
     * @return
     */
    private <T> T getBean(Class<T> clazz ,HttpServletRequest request){
        BeanFactory factory = (BeanFactory) WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        return factory.getBean(clazz);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
