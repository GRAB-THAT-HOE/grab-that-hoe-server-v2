package com.moreversal.grabthathoe.user.domain.dto;

import com.moreversal.grabthathoe.user.domain.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserJoinDto {

    @NotBlank
    private String phone;

    @NotBlank
    private String name;

    @NotNull
    private LocalDateTime birthDate;

    @NotBlank
    private UserRole userRole;
}
