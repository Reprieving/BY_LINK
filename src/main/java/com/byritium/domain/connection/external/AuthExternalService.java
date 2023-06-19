package com.byritium.domain.connection.external;

import com.byritium.application.dto.ConnectionDto;

public interface AuthExternalService {

    void auth(ConnectionDto connectionDto);
}
