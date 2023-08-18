package com.byritium.persistence.convertor;

import com.byritium.domain.account.entity.Account;
import com.byritium.domain.account.entity.AccountIdentifier;
import com.byritium.persistence.po.AccountIdentifierPo;
import com.byritium.persistence.po.AccountPo;
import com.byritium.types.constance.ObjectState;

import java.time.LocalDateTime;

public class AccountConvertor {
    public static AccountIdentifier convertAgg(AccountIdentifierPo accountIdentifierPo) {
        if (accountIdentifierPo == null) {
            return null;
        }
        AccountIdentifier accountIdentifier = new AccountIdentifier();
        return accountIdentifier;
    }

    public static AccountPo convertAccountPo(Account account) {
        if (account == null)
            return null;
        AccountPo po = new AccountPo();
        po.setAccountName(account.getAccountName());
        po.setAccountSecret(account.getAccountSecret());
        po.setCreateTime(LocalDateTime.now());
        po.setOs(ObjectState.ENABLE);

        return po;
    }

    public static AccountIdentifierPo convertAccountIdentifierPo(AccountIdentifier accountIdentifier) {
        if (accountIdentifier == null) {
            return null;
        }
        AccountIdentifierPo po = new AccountIdentifierPo();
        return po;
    }

    public static AccountIdentifier convertAccountIdentifierPo(AccountIdentifierPo accountIdentifierPo) {
        if (accountIdentifierPo == null) {
            return null;
        }
        AccountIdentifier po = new AccountIdentifier();
        return po;
    }

    public static Account convertAgg(AccountPo accountPo) {
        if (accountPo == null)
            return null;
        return Account.builder()
                .id(accountPo.getId())
                .accountName(accountPo.getAccountName())
                .accountSecret(accountPo.getAccountSecret())
                .createTime(accountPo.getCreateTime())
                .os(accountPo.getOs())
                .build();
    }
}
