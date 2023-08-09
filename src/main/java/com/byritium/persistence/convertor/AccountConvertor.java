package com.byritium.persistence.convertor;

import com.byritium.application.command.AccountCommand;
import com.byritium.domain.account.entity.Account;
import com.byritium.domain.account.entity.AccountAuth;
import com.byritium.persistence.po.AccountAuthPo;
import com.byritium.persistence.po.AccountPo;

public class AccountConvertor {
    public static AccountAuth convertAgg(AccountAuthPo accountAuthPo){
        AccountAuth accountAuth = new AccountAuth();
        return accountAuth;
    }

    public static AccountPo convertAccountPo(AccountCommand accountCommand){
        AccountPo po = new AccountPo();
        return po;
    }
}
