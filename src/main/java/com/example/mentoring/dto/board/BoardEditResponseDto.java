package com.example.mentoring.dto.board;

import com.example.mentoring.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardEditResponseDto {
    private String title;

    private String content;

    public BoardEditResponseDto toDto(BoardEntity boardEntity){
        return new BoardEditResponseDto(boardEntity.getTitle(), boardEntity.getContent());
    }
}
