package com.moreversal.grabthathoe.connection.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class CreateConnectionDto {

    @NotBlank
    private Long postingId;
}
