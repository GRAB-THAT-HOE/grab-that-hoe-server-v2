package com.moreversal.grabthathoe.connection.service;

import com.moreversal.grabthathoe.common.exception.ForbiddenException;
import com.moreversal.grabthathoe.common.exception.RecordNotFoundException;
import com.moreversal.grabthathoe.connection.domain.dto.CreateConnectionDto;
import com.moreversal.grabthathoe.connection.domain.entity.Connection;
import com.moreversal.grabthathoe.connection.domain.enums.ConnectionStatus;
import com.moreversal.grabthathoe.connection.domain.repository.ConnectionRepository;
import com.moreversal.grabthathoe.posting.domain.entity.Posting;
import com.moreversal.grabthathoe.posting.domain.repository.PostingRepository;
import com.moreversal.grabthathoe.user.domain.entity.User;
import com.moreversal.grabthathoe.user.domain.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConnectionServiceImpl implements ConnectionService {

    private final ConnectionRepository connectionRepository;
    private final PostingRepository postingRepository;

    @Override
    public List<Connection> getConnectionsByPostingId(Long postingId) {

        Posting posting = postingRepository.findById(postingId)
                .orElseThrow(RecordNotFoundException::new);

        return connectionRepository.findAllByPosting(posting);
    }

    @Override
    public Connection createConnection(CreateConnectionDto dto, User user) {

        Posting posting = postingRepository.findById(dto.getPostingId())
                .orElseThrow(RecordNotFoundException::new);

        if(user.getUserRole().equals(UserRole.FARMER)) {
            throw new ForbiddenException("농장주는 연결을 할 수 없습니다.");
        }

        Connection connection = Connection.builder()
                .posting(posting)
                .worker(user)
                .status(ConnectionStatus.NORMAL)
                .regId("test")
                .updId("test")
                .build();

        return connectionRepository.save(connection);
    }
}
