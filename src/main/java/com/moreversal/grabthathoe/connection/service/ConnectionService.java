package com.moreversal.grabthathoe.connection.service;

import com.moreversal.grabthathoe.connection.domain.dto.CreateConnectionDto;
import com.moreversal.grabthathoe.connection.domain.entity.Connection;
import com.moreversal.grabthathoe.user.domain.entity.User;

import java.util.List;

public interface ConnectionService {

    List<Connection> getConnectionsByPostingId(Long postingId);
    Connection createConnection(CreateConnectionDto dto, User user);
}
