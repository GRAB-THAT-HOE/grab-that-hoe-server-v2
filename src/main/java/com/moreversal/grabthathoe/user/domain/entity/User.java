package com.moreversal.grabthathoe.user.domain.entity;

import com.moreversal.grabthathoe.user.domain.enums.Role;
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
    private Long idx;

    @Column(nullable = false, length = 11)
    private String phone;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(name="birth_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(nullable = false, length = 10)
    private String status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(name = "reg_id", nullable = false, length = 45)
    private String regId;

    @Column(name = "reg_dt", nullable = false)
    private LocalDateTime regDt;

    @Column(name = "upd_id", nullable = false, length = 45)
    private String updId;

    @Column(name = "upd_dt", nullable = false)
    private LocalDateTime updDt;

    @Builder
    public User(String phone, String name, Date birthDate, String status, Role role) {
        Assert.hasText(phone, "phone number must not be empty");
        Assert.hasText(name, "name must not be empty");
        Assert.hasText(status, "status must not be empty");
        Assert.isInstanceOf(Role.class, role, "role must be Role type");

        this.phone = phone;
        this.name = name;
        this.birthDate = birthDate;
        this.status = status;
        this.role = role;
    }

}
