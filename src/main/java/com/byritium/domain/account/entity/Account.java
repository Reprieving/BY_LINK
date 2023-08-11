package com.byritium.domain.account.entity;

import com.byritium.types.constance.ObjectState;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Account {
    private Long id;
    private Long appId;
    private Long uid;
    private LocalDateTime createTime;
    private ObjectState os;
    private AccountIdentifier auth;
}
