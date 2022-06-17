package com.moreversal.grabthathoe.user.domain.entity;

import com.moreversal.grabthathoe.user.domain.enums.UserRole;
import com.moreversal.grabthathoe.user.domain.enums.UserStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity(name = "user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 11, unique = true)
    private String phone;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(name="birth_date", nullable = false)
    private LocalDateTime birthDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole userRole;

    @Column(nullable = false, length = 45)
    private String regId;

    @Column(nullable = false)
    private LocalDateTime regDt;

    @Column(nullable = false, length = 45)
    private String updId;

    @Column(nullable = false)
    private LocalDateTime updDt;

    @Builder
    public User(String phone, String name, LocalDateTime birthDate, UserStatus status, UserRole userRole, String regId, String updId) {

        Assert.hasText(phone, "phone number must not be empty");
        Assert.hasText(name, "name must not be empty");
        Assert.isInstanceOf(UserStatus.class, status, "status must not be empty");
        Assert.isInstanceOf(UserRole.class, userRole, "role must be Role type");
        Assert.hasText(regId, "regId must not be empty");
        Assert.hasText(updId, "updId must not be empty");

        this.phone = phone;
        this.name = name;
        this.birthDate = birthDate;
        this.status = status;
        this.userRole = userRole;
        this.regId = regId;
        this.updId = updId;
    }

}
