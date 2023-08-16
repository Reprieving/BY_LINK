package com.byritium.domain.account.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AccountIdentifier {
    private Long accountId;
    private String name;
    private String identifier;
}
