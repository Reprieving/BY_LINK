package com.byritium.persistence.convertor;

import com.byritium.domain.group.entity.Group;
import com.byritium.domain.group.entity.GroupMember;
import com.byritium.persistence.po.GroupMemberPo;
import com.byritium.persistence.po.GroupPo;

public class GroupConvertor {
    public static GroupPo convertorGroupPo(Group group){
        GroupPo groupPo = new GroupPo();
        return groupPo;
    }

    public static GroupMemberPo convertorGroupMemberPo(GroupMember groupMember){
        GroupMemberPo po = new GroupMemberPo();
        return po;
    }
}
