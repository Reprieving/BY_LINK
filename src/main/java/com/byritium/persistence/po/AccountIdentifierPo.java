package com.byritium.persistence.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.byritium.types.constance.ObjectState;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_account_identifier")
public class AccountIdentifierPo {
    private Long id;
    private Long accountId;
    private String name;
    private String identifier;
    private LocalDateTime createTime;
    private ObjectState os;
}
