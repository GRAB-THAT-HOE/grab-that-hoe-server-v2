package com.moreversal.grabthathoe.posting.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CreatePostingDto {

    @NotBlank
    private String title;

    @NotBlank
    private String explanation;

    @NotBlank
    private int maxWorker;

    @NotBlank
    private boolean giveRoomAndMeal;

    @NotBlank
    private boolean giveSnack;

    @NotBlank
    private boolean image;

    @NotBlank
    private LocalDateTime startWorkingDate;

    @NotBlank
    private LocalDateTime endWorkingDate;

    @NotBlank
    private Long pay;
}
