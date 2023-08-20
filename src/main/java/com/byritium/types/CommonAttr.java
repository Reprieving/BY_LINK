package com.byritium.types;

import com.byritium.types.constance.ObjectState;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommonAttr {
    private LocalDateTime createTime;
    private ObjectState os;
}
