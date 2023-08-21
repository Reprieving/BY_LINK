package com.byritium.domain.group.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupAuth {
    private Long groupId;
    private Long accountId;
}
