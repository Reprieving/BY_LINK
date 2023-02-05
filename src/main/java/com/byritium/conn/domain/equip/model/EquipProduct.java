package com.byritium.conn.domain.equip.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EquipProduct {
    private long id;
    private String productName; //产品名
    private long sortId;//种类id
    private String remark;//备注
    private LocalDateTime createTime;
    private long creator;
    private LocalDateTime updateTime;
    private long updater;
}
