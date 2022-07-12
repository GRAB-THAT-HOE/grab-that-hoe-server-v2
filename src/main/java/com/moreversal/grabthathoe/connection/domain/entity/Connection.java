package com.moreversal.grabthathoe.connection.domain.entity;

import com.moreversal.grabthathoe.connection.domain.enums.ConnectionStatus;
import com.moreversal.grabthathoe.posting.domain.entity.Posting;
import com.moreversal.grabthathoe.user.domain.entity.User;
import io.jsonwebtoken.lang.Assert;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "connection")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Connection {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id")
    private User worker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posting_id")
    private Posting posting;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ConnectionStatus status;

    @Column(nullable = false, length = 45)
    private String regId;

    @Column(nullable = false)
    private LocalDateTime regDt;

    @Column(nullable = false, length = 45)
    private String updId;

    @Column(nullable = false)
    private LocalDateTime updDt;

    @Builder
    public Connection(User worker, Posting posting, ConnectionStatus status, String regId, String updId) {

        Assert.isInstanceOf(ConnectionStatus.class, status, "status must not be empty");
        org.springframework.util.Assert.hasText(regId, "regId must not be empty");
        org.springframework.util.Assert.hasText(updId, "updId must not be empty");

        this.worker = worker;
        this.posting = posting;
        this.status = status;
        this.regId = regId;
        this.updId = updId;
    }
}
