package com.byritium.domain.account.service;

import com.byritium.domain.account.entity.AccountIdentifier;
import com.byritium.domain.account.repository.AccountRepository;
import com.byritium.persistence.convertor.AccountConvertor;
import com.byritium.persistence.po.AccountIdentifierPo;
import com.byritium.types.exception.AccountAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountAuthServiceImpl implements AccountAuthService {
    @Autowired
    private AccountRepository accountRepository;

    public AccountIdentifier authenticate(String identifier) {
        AccountIdentifier accountIdentifier = accountRepository.findAccountIdentifier(null, identifier);
        if (accountIdentifier == null) {
            throw new AccountAuthException("auth failure");
        }
        return accountIdentifier;
    }
}
