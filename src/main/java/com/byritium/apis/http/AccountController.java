package com.byritium.apis.http;

import com.byritium.apis.model.AccountLoginRsp;
import com.byritium.application.AccountAppService;
import com.byritium.application.command.AccountCommand;
import com.byritium.application.command.AccountIdentifierCommand;
import com.byritium.domain.account.entity.Account;
import com.byritium.domain.account.entity.AccountIdentifier;
import com.byritium.types.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    private AccountAppService accountAppService;

    @RequestMapping("register")
    public ResponseEntity<?> register(AccountCommand accountCommand) {
        accountAppService.resisterAccount(accountCommand);
        return ResponseEntity.ok()
                .body(new ResponseBody<>());
    }

    @RequestMapping("identifier/create")
    public ResponseEntity<?> createIdentifier(AccountIdentifierCommand command) {
        accountAppService.createIdentifier(command);
        return ResponseEntity.ok()
                .body(new ResponseBody<>());
    }

    @RequestMapping("login")
    public ResponseEntity<?> login(AccountCommand command) {
        String token = accountAppService.login(command);
        AccountLoginRsp rsp = new AccountLoginRsp(token);
        return ResponseEntity.ok()
                .body(new ResponseBody<>(rsp));
    }

    @RequestMapping("logout")
    public byte[] logout() {
        return null;
    }

}
