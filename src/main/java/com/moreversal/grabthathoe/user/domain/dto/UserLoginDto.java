package com.moreversal.grabthathoe.user.domain.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class UserLoginDto {

    @NotBlank
    private String phone;

    public UserLoginDto(String phone) {
        this.phone = phone;
    }
}
