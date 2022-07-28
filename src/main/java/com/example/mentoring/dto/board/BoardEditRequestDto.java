package com.example.mentoring.dto.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardEditRequestDto {
    @NotBlank(message = "게시물 제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "게시물 내용을 입력해주세요.")
    private String content;
}
