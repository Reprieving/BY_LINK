package com.byritium.application;

import com.byritium.application.command.AccountCommand;
import com.byritium.application.command.AccountIdentifierCommand;
import com.byritium.domain.account.entity.Account;
import com.byritium.domain.account.entity.AccountIdentifier;
import com.byritium.domain.account.repository.AccountRepository;
import com.byritium.domain.group.entity.GroupMember;
import com.byritium.domain.group.repository.GroupRepository;
import com.byritium.types.constance.ObjectState;
import com.byritium.types.constance.ResultEnum;
import com.byritium.types.exception.BusinessException;
import com.byritium.utils.AccountHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
public class AccountAppService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private GroupRepository groupRepository;

    public void resisterAccount(AccountCommand accountCommand) {
        Account account = Account.builder()
                .accountName(accountCommand.getAccountName())
                .accountSecret(accountCommand.getAccountSecret())
                .createTime(LocalDateTime.now())
                .os(ObjectState.ENABLE)
                .build();

        accountRepository.saveAccount(account);
    }

    public void createIdentifier(AccountIdentifierCommand command) {
        Long accountId = AccountHolder.get();
        Account account = accountRepository.findAccountById(accountId);

        AccountIdentifier accountIdentifier = AccountIdentifier.builder()
                .accountId(account.getId())
                .name(command.getName())
                .identifier(UUID.randomUUID().toString())
                .build();

        accountRepository.saveAccountIdentifier(account, accountIdentifier);
    }

    public void joinGroup() {
        GroupMember groupMember = new GroupMember();
        groupRepository.saveGroupMember(groupMember);
    }

    public void quitGroup() {
        GroupMember groupMember = new GroupMember();
        groupRepository.removeGroupMember(groupMember);
    }
}
