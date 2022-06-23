package com.moreversal.grabthathoe.posting.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class UpdatePostingDto {

    @NotBlank
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String explanation;
}
