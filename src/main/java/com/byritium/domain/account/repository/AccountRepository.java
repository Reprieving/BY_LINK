package com.byritium.domain.account.repository;

import com.byritium.domain.account.entity.Account;
import com.byritium.domain.account.entity.vo.AccountAuth;
import com.byritium.types.external.ConnectionAuth;

public interface AccountRepository {
    Account getAccountAuth(AccountAuth accountAuth);
}
