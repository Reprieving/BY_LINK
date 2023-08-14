package com.byritium.interceptor;

import com.byritium.types.constance.ResultEnum;
import com.byritium.types.constance.TokenVerify;
import com.byritium.types.exception.BizException;
import com.byritium.types.exception.TokenVerifyException;
import com.byritium.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("access_token");

        try {
            Claims claims = JwtUtils.verifyToken(token);
            String accountId = (String) claims.get("id");

            return true;
        } catch (Exception e){
            log.error("verify token error");
            throw new BizException(ResultEnum.ACCOUNT_VERIFY_FAIL);
        }
    }

}
