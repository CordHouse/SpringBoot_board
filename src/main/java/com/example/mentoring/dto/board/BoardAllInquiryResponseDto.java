package com.example.mentoring.dto.board;

import com.example.mentoring.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
// 전체 조회 커스텀
public class BoardAllInquiryResponseDto {
    private List<String[]> inquiry = new LinkedList<>();

    public BoardAllInquiryResponseDto toDto(List<BoardEntity> boardEntityList){
        while(!boardEntityList.isEmpty()){
            inquiry.add(new String[]{
                    "title : " + boardEntityList.get(0).getTitle(),
                    "writer : " + boardEntityList.get(0).getWriter()});
            boardEntityList.remove(0);
        }
        return new BoardAllInquiryResponseDto(inquiry);
    }
}
