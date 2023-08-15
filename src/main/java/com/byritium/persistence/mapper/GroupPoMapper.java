package com.byritium.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.byritium.domain.group.entity.Group;
import com.byritium.persistence.po.AccountPo;
import com.byritium.persistence.po.GroupPo;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupPoMapper extends BaseMapper<GroupPo> {
}
