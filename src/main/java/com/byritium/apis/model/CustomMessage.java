package com.byritium.apis.model;

import lombok.Data;

@Data
public class CustomMessage {
    private CustomMessageHeader header;
    private CustomMessage body;
}
