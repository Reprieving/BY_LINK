package com.byritium.persistence.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.byritium.types.CommonAttr;
import com.byritium.types.constance.ObjectState;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_group")
public class GroupPo extends CommonAttr {
    private Long id;
    private Long accountId;
    private Long creatorIdentifier;
    private String name;
}
