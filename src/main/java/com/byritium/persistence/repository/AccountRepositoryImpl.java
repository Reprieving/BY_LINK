package com.byritium.persistence.repository;

import com.byritium.domain.account.entity.Account;
import com.byritium.domain.account.repository.AccountRepository;
import com.byritium.persistence.po.AccountAuthPo;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepositoryImpl implements AccountRepository {
    @Override
    public void saveAccount(Account account) {
        
    }

    @Override
    public AccountAuthPo getAccountAuth(String username,String identifier) {
        return null;
    }
}
