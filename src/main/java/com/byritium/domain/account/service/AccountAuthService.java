package com.byritium.domain.account.service;

import com.byritium.domain.account.entity.AccountAuth;

public interface AccountAuthService {
    AccountAuth authenticate(String identifier);
}
