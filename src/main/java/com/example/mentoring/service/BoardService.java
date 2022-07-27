package com.example.mentoring.service;

import com.example.mentoring.dto.board.BoardRequestDto;
import com.example.mentoring.dto.board.BoardResponseDto;
import com.example.mentoring.entity.BoardEntity;
import com.example.mentoring.exception.BoardNotFoundException;
import com.example.mentoring.exception.IdNotFoundException;
import com.example.mentoring.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
* Service 구간에서는 모든 함수에 @Transactional 해주는 것이 좋다. 이유는 데이터 베이스의 값을 저장시킬때 A와 B 사이의 데이터 교류의 오류가 없어야 하기 때문에 모든 과정이 정상 처리되었을때 저장하기 때문이다.
* 질문 # = 1 */

@Service
@RequiredArgsConstructor
public class BoardService {
    // # 여기에 final을 넣어줘야 하는 이유가 있을까?
    private final BoardRepository boardRepository;

    // 데이터 전체 조회
    // http://localhost:8080/boards
    @Transactional(readOnly = true) // 이 어노테이션을 사용하면 더티체킹이 일어나서, 저장하지 않아도 메서드가 성공적으로 끝난다면 저장한다.
    public List<BoardEntity> getBoards(){
        List<BoardEntity> boards = boardRepository.findAll();
        return boards;
    }

    // 데이터 단일 조회
    // http://localhost:8080/boards/1
    @Transactional(readOnly = true)
    public BoardEntity getBoard(Long id){
        return boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
    }

    // 데이터 저장
    // http://localhost:8080/boards
    @Transactional
    public BoardResponseDto save(BoardRequestDto boardRequestDto){
        BoardEntity boardEntity = new BoardEntity(boardRequestDto.getTitle(), boardRequestDto.getContent(), boardRequestDto.getWriter());
        boardRepository.save(boardEntity);
        return new BoardResponseDto().toDto(boardEntity);
    }

    // 데이터 단일 수정
    // http://localhost:8080/boards/1
    @Transactional
    public BoardEntity editBoard(Long id, BoardEntity board){
        BoardEntity boardEntity = boardRepository.findById(id).orElseThrow(IdNotFoundException::new);
        boardEntity.setTitle(board.getTitle()); // 데이터 베이스의 데이터를 가져와 현재 입력받은 값을 수정하여 저장한다
        boardEntity.setContent(board.getContent()); // 위와 동일
        return boardEntity;
    }

    // 데이터 삭제
    // http://localhost:8080/boards/1
    @Transactional
    public void deleteBoard(Long id){
        boardRepository.deleteById(id);
    }

    // 데이터 모두 삭제
    @Transactional
    public void allDeleteBoard(){
        boardRepository.deleteAll();
    }
}
