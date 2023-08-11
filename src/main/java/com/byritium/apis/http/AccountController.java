package com.byritium.apis.http;

import com.byritium.application.AccountAppService;
import com.byritium.application.command.AccountCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    private AccountAppService accountAppService;

    @RequestMapping("register")
    public void register(AccountCommand accountCommand) {
        accountAppService.resisterAccount(accountCommand);
    }

    @RequestMapping("identifier/create")
    public void createIdentifier(AccountCommand accountCommand) {
        accountAppService.createIdentifier(accountCommand);
    }

    @RequestMapping("logout")
    public void logout() {

    }

}
