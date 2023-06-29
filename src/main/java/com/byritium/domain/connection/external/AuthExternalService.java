package com.byritium.domain.connection.external;

import com.byritium.types.external.ConnectionAuth;

public interface AuthExternalService {

    void auth(ConnectionAuth connectionAuth);
}
