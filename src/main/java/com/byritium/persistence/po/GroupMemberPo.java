package com.byritium.persistence.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.byritium.types.CommonAttr;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_group_member")
public class GroupMemberPo extends CommonAttr {
    private Long id;
    private Long accountId;
    private Long groupId;
    private Long accountIdentifier;
}
