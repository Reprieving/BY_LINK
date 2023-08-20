package com.byritium.persistence.convertor;

import com.byritium.domain.group.entity.Group;
import com.byritium.domain.group.entity.GroupMember;
import com.byritium.persistence.po.GroupMemberPo;
import com.byritium.persistence.po.GroupPo;
import com.byritium.types.constance.ObjectState;

import java.time.LocalDateTime;

public class GroupConvertor {
    public static GroupPo convertorGroupPo(Group group) {
        GroupPo groupPo = new GroupPo();
        groupPo.setName(group.getGroupName());
        groupPo.setAccountId(group.getAccountId());
        groupPo.setCreatorIdentifier(groupPo.getCreatorIdentifier());
        groupPo.setCreateTime(group.getCreateTime());
        groupPo.setOs(ObjectState.ENABLE);
        return groupPo;
    }

    public static GroupMemberPo convertorGroupMemberPo(GroupMember groupMember){
        GroupMemberPo po = new GroupMemberPo();
        return po;
    }
}
