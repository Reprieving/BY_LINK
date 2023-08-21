package com.byritium.domain.group.service;

import com.byritium.domain.group.dto.GroupAuth;
import com.byritium.domain.group.entity.Group;

public interface GroupAuthService {
    Group authenticate(GroupAuth groupAuth);
}
