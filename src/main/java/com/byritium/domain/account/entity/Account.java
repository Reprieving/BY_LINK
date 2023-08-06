package com.byritium.domain.account.entity;

import com.byritium.types.constance.ObjectState;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Account {
    private Long id;
    private Long appId;
    private Long uid;
    private Long identifier;
    private LocalDateTime createTime;
    private ObjectState os;
    private AccountAuth auth;
}
