package com.byritium.domain.account.entity;

import com.byritium.domain.account.entity.vo.AccountAuth;
import com.byritium.domain.account.repository.AccountRepository;
import com.byritium.types.exception.AccountAuthException;
import com.byritium.types.external.ConnectionAuth;
import com.byritium.utils.SpringUtils;
import lombok.Getter;

@Getter
public class Account {
    private AccountAuth auth;

    public AccountAuth authenticate(AccountAuth accountAuth){
        AccountRepository accountRepository = SpringUtils.getBean(AccountRepository.class);
        Account account = accountRepository.getAccountAuth(accountAuth);
        if(account == null){
            throw new AccountAuthException("auth failure");
        }
        return account.getAuth();
    }
}
