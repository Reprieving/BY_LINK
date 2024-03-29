package com.byritium.domain.account.service;

import com.byritium.domain.account.entity.AccountIdentifier;

public interface AccountAuthService {
    AccountIdentifier authenticate(Long accountId, String identifier);
}
