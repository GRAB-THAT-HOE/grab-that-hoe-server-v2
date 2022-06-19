package com.moreversal.grabthathoe.user.domain.dto;

import com.moreversal.grabthathoe.user.domain.enums.UserRole;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
public class UserJoinDto {

    @NotBlank
    private String phone;

    @NotBlank
    private String name;

    @NotNull
    private LocalDateTime birthDate;

    @NotNull
    private UserRole userRole;

    public UserJoinDto(String phone, String name, LocalDateTime birthDate, UserRole userRole) {
        this.phone = phone;
        this.name = name;
        this.birthDate = birthDate;
        this.userRole = userRole;
    }
}
