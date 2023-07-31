package com.byritium.external.impl;

import com.byritium.domain.connection.external.AuthExternalService;
import com.byritium.types.exception.AccountAuthException;
import com.byritium.types.external.ConnectionAuth;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AccountAuthExternalService implements AuthExternalService {

    @Override
    public void auth(ConnectionAuth connectionAuth) {
        String identify = connectionAuth.getIdentify();
        if(!StringUtils.hasText(identify)){
            throw new AccountAuthException("authentic fail");
        }
    }
}
