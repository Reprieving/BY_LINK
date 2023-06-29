package com.byritium.persistence.convertor;

import com.byritium.domain.account.entity.AccountAuth;
import com.byritium.persistence.po.AccountAuthPo;

public class AccountAuthConvertor {
    public static AccountAuth convertAgg(AccountAuthPo accountAuthPo){
        AccountAuth accountAuth = new AccountAuth();
        return accountAuth;
    }
}
