package com.example.mentoring.dto.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardRequestDto {
    // NotBlank = String 문자열된 경우
    // NotNull = Integer, Long 정수형의 경우
    // 프런트 -> 서버 데이터 전송
    // DTO -> error 발생 -> exceptionAdvice에서 catch -> msg 출력
    @NotBlank(message = "게시물 제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "게시물 내용을 입력해주세요.")
    private String content;

    @NotBlank(message = "게시물 작성자를 입력해주세요.")
    private String writer;
}
