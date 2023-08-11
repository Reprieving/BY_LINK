package com.byritium.persistence.convertor;

import com.byritium.domain.account.entity.Account;
import com.byritium.domain.account.entity.AccountIdentifier;
import com.byritium.persistence.po.AccountIdentifierPo;
import com.byritium.persistence.po.AccountPo;

public class AccountConvertor {
    public static AccountIdentifier convertAgg(AccountIdentifierPo accountIdentifierPo){
        AccountIdentifier accountIdentifier = new AccountIdentifier();
        return accountIdentifier;
    }

    public static AccountPo convertAccountPo(Account account){
        AccountPo po = new AccountPo();
        return po;
    }

    public static AccountIdentifierPo convertAccountIdentifierPo(Account account){
        AccountIdentifierPo po = new AccountIdentifierPo();
        return po;
    }

    public static Account convertAgg(AccountPo accountPo){
        Account account = new Account();
        return account;
    }
}
