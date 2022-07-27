package com.example.mentoring.dto.board;

import com.example.mentoring.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardResponseDto {
    // 서버 -> 프런트 응답을 줄때 민감 정보를 가릴 수 있다. 빼주면됨 해당 변수를?
    // DTO -> error 발생 -> exceptionAdvice에서 catch -> msg 출력
    private Long id;

    private String title;

    private String content;

    private String writer;

    public BoardResponseDto toDto(BoardEntity boardEntity){
        // 저장된 변수를 리턴해주는데, 이때 민감정보는 빼주게 되면 반환되지 않는다.
        return new BoardResponseDto(boardEntity.getId(), boardEntity.getTitle(), boardEntity.getContent(), boardEntity.getWriter());
    }
}
