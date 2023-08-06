package com.byritium.domain.account.repository;

import com.byritium.domain.account.entity.Account;
import com.byritium.persistence.po.AccountAuthPo;

public interface AccountRepository {

    void saveAccount(Account account);

    AccountAuthPo getAccountAuth(String username, String identifier);
}
