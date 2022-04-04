package com.moreversal.grabthathoe.user.domain.entity;

import com.moreversal.grabthathoe.user.domain.enums.Role;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity(name = "user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable = false, length = 11)
    private String phone;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(name="birth_date", nullable = false)
    private Date birthDate;

    @Column(nullable = false, length = 10)
    private String status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(name = "reg_id", nullable = false, length = 45)
    private String regId;

    @Column(name = "reg_dt", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime regDt;

    @Column(name = "upd_id", nullable = false, length = 45)
    private String updId;

    @Column(name = "upd_dt", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updDt;

}
