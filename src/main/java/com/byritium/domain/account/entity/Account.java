package com.byritium.domain.account.entity;

import com.byritium.types.CommonAttr;
import com.byritium.types.constance.ObjectState;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Account extends CommonAttr {
    private Long id;
    private Long appId;
    private String accountName;
    private String accountSecret;
    private List<AccountIdentifier> identifierList;
}
