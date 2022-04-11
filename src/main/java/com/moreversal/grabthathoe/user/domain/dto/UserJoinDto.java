package com.moreversal.grabthathoe.user.domain.dto;

import com.moreversal.grabthathoe.user.domain.enums.Role;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
public class UserJoinDto {

    @NotBlank
    String phone;

    @NotBlank
    String name;

    @NotNull
    Date birthDate;

    @NotNull
    Role role;

}
