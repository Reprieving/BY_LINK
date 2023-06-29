package com.byritium.domain.account.repository;

import com.byritium.persistence.po.AccountAuthPo;

public interface AccountRepository {
    AccountAuthPo getAccountAuth(String username, String identifier);
}
