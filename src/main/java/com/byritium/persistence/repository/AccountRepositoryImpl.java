package com.byritium.persistence.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.byritium.domain.account.entity.Account;
import com.byritium.domain.account.entity.AccountIdentifier;
import com.byritium.domain.account.repository.AccountRepository;
import com.byritium.persistence.convertor.AccountConvertor;
import com.byritium.persistence.mapper.AccountIdentifierPoMapper;
import com.byritium.persistence.mapper.AccountPoMapper;
import com.byritium.persistence.po.AccountIdentifierPo;
import com.byritium.persistence.po.AccountPo;
import com.byritium.types.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

    @Autowired
    private AccountPoMapper accountPoMapper;

    @Autowired
    private AccountIdentifierPoMapper accountIdentifierPoMapper;

    @Override
    public void saveAccount(Account account) {
        List<AccountPo> list = accountPoMapper.selectList(
                new LambdaQueryWrapper<AccountPo>()
                        .eq(AccountPo::getAppId, account.getAppId())
        );

        if (list.size() > 0) {
            throw new BizException("account exist");
        }

        AccountPo accountPo = AccountConvertor.convertAccountPo(account);
        accountPoMapper.insert(accountPo);
    }

    @Override
    public void saveAccountIdentifier(Account account, AccountIdentifier accountIdentifier) {
        List<AccountIdentifierPo> list = accountIdentifierPoMapper.selectList(
                new LambdaQueryWrapper<AccountIdentifierPo>()
                        .eq(AccountIdentifierPo::getAccountId, account.getId())
                        .eq(AccountIdentifierPo::getIdentify,accountIdentifier.getIdentifier())
        );

        if (list.size() > 0) {
            throw new BizException("account exist");
        }

        AccountPo accountPo = AccountConvertor.convertAccountPo(account);
        accountPoMapper.insert(accountPo);
    }

    @Override
    public AccountIdentifierPo getAccountAuth(String username, String identifier) {
        return null;
    }
}
