package com.byritium.domain.account.repository;

import com.byritium.domain.account.entity.Account;
import com.byritium.domain.account.entity.AccountIdentifier;
import com.byritium.persistence.po.AccountIdentifierPo;

public interface AccountRepository {

    void saveAccount(Account account);

    Account findAccountById(Long accountId);

    Account findAccountByName(String name);

    void saveAccountIdentifier(Account account, AccountIdentifier accountIdentifier);

    AccountIdentifier findAccountIdentifier(Long accountId,String identifier);

    AccountIdentifierPo getAccountAuth(String username, String identifier);
}
