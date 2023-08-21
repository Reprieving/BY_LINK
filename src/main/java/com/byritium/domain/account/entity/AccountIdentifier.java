package com.byritium.domain.account.entity;

import com.byritium.types.CommonAttr;
import com.byritium.types.constance.ObjectState;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class AccountIdentifier {
    private Long accountId;
    private String name;
    private String identifier;
    private LocalDateTime createTime;
    private ObjectState os;
}
