package com.byritium.persistence.po;

import com.byritium.types.constance.ObjectState;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountPo {
    private Long id;
    private Long appId;
    private Long uid;
    private String accountName;
    private String accountSecret;
    private LocalDateTime createTime;
    private ObjectState os;
}
