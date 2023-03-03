package com.byritium.conn.apis.model;

import com.byritium.conn.infra.general.constance.CustomerType;
import lombok.Data;

@Data
public class CustomMessageBody {
    private CustomerType customerType;
}
