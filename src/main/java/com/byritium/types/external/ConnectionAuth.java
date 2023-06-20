package com.byritium.types.external;

import lombok.Data;

@Data
public class ConnectionAuth {
    private String username;
    private String password;
    private String identify;
}
