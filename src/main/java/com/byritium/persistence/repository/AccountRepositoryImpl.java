package com.byritium.persistence.repository;

import com.byritium.domain.account.entity.Account;
import com.byritium.domain.account.entity.vo.AccountAuth;
import com.byritium.domain.account.repository.AccountRepository;
import com.byritium.types.external.ConnectionAuth;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepositoryImpl implements AccountRepository {
    @Override
    public Account getAccountAuth(AccountAuth accountAuth) {
        return null;
    }
}
