package com.byritium.persistence.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.byritium.types.constance.ObjectState;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_account")
public class AccountPo {
    private Long id;
    private String accountName;
    private String accountSecret;
    private LocalDateTime createTime;
    private ObjectState os;
}
