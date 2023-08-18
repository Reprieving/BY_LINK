package com.byritium.interceptor;

import com.byritium.types.constance.ResultEnum;
import com.byritium.types.exception.BusinessException;
import com.byritium.utils.AccountHolder;
import com.byritium.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("access_token");

        try {
            String id = JwtUtils.get(token);
            if (id == null) {
                throw new BusinessException(ResultEnum.ACCOUNT_VERIFY_FAIL);
            }
            Long accountId = Long.valueOf(id);
            AccountHolder.set(accountId);
            return true;
        } catch (Exception e) {
            log.error("verify token error");
            throw new BusinessException(ResultEnum.ACCOUNT_VERIFY_FAIL);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        AccountHolder.clear();
    }

}
