package com.byritium.domain.account.entity;

import com.byritium.types.constance.ObjectState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AccountIdentifier {
    private Long accountId;
    private String name;
    private String identifier;
    private LocalDateTime createTime;
    private ObjectState os;
}
