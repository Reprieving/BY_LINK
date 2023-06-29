package com.byritium.domain.account.service;

import com.byritium.domain.account.entity.AccountAuth;
import com.byritium.domain.account.repository.AccountRepository;
import com.byritium.persistence.convertor.AccountAuthConvertor;
import com.byritium.persistence.po.AccountAuthPo;
import com.byritium.types.exception.AccountAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountAuthServiceImpl implements AccountAuthService {
    @Autowired
    private AccountRepository accountRepository;

    public AccountAuth authenticate(AccountAuth accountAuth) {
        AccountAuthPo po = accountRepository.getAccountAuth(accountAuth.getUsername(), accountAuth.getIdentifier());
        if (po == null) {
            throw new AccountAuthException("auth failure");
        }
        return AccountAuthConvertor.convertAgg(po);
    }
}
