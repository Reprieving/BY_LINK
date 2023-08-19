package com.byritium.persistence.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.byritium.types.CommonAttr;
import com.byritium.types.constance.ObjectState;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_account")
public class AccountPo extends CommonAttr {
    private Long id;
    private String accountName;
    private String accountSecret;
}
