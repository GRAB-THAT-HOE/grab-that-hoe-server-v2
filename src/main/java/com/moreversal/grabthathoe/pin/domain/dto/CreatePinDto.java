package com.moreversal.grabthathoe.pin.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class CreatePinDto {

    @NotBlank
    private Long postingId;
}
