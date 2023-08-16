package com.byritium.persistence.convertor;

import com.byritium.domain.account.entity.Account;
import com.byritium.domain.account.entity.AccountIdentifier;
import com.byritium.persistence.po.AccountIdentifierPo;
import com.byritium.persistence.po.AccountPo;
import com.byritium.types.constance.ObjectState;

import java.time.LocalDateTime;

public class AccountConvertor {
    public static AccountIdentifier convertAgg(AccountIdentifierPo accountIdentifierPo){
        AccountIdentifier accountIdentifier = new AccountIdentifier();
        return accountIdentifier;
    }

    public static AccountPo convertAccountPo(Account account){
        AccountPo po = new AccountPo();
        po.setAccountName(account.getAccountName());
        po.setAccountSecret(account.getAccountSecret());
        po.setCreateTime(LocalDateTime.now());
        po.setOs(ObjectState.ENABLE);

        return po;
    }

    public static AccountIdentifierPo convertAccountIdentifierPo(AccountIdentifier accountIdentifier){
        AccountIdentifierPo po = new AccountIdentifierPo();
        return po;
    }

    public static AccountIdentifier convertAccountIdentifierPo(AccountIdentifierPo accountIdentifierPo){
        AccountIdentifier po = new AccountIdentifier();
        return po;
    }

    public static Account convertAgg(AccountPo accountPo){
        Account account = new Account();
        return account;
    }
}
