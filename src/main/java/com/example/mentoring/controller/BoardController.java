package com.example.mentoring.controller;

import com.example.mentoring.entity.BoardEntity;
import com.example.mentoring.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/*
* GetMapping : 데이터베이스에서 데이터를 가져와 사용할 경우 (id값 필요할 수도 있고 없을 수도 있음)
* PostMapping : 데이터베이스에 데이터를 저장해야 하는 경우
* PutMapping : 데이터베이스의 데이터 내용을 수정해야 하는 경우 (id값 필요)
* DeleteMapping : 데이터베이스의 데이터를 지울때 사용 (id값 필요)
*
* 질문 # = 1*/

// ResponseEntity<>(출력(return), http통신 수신여부)로 입력하기 때문에 출력값에 변화를 주고 싶다면 출력부분을 수정해야한다.

@Controller
@RequiredArgsConstructor // @Non null + static 생성자
public class BoardController {
    private final BoardService boardService;

    // Get 방식
    // 데이터 전체 조회
    @GetMapping("/boards")
    public ResponseEntity<?> getBoards(){
        return new ResponseEntity<>(boardService.getBoards(), HttpStatus.OK);
    }

    // Get 방식
    // 데이터 단일 조회 (id 값 필요)
    // id 값이 쓰인다면 파라미터에 (@PathVariable("아이디") Type 변수)로 작성
    @GetMapping("/boards/{id}")
    public ResponseEntity<?> getBoard(@PathVariable("id") Long id){
        return new ResponseEntity<>(boardService.getBoard(id), HttpStatus.OK);
    }
    // Post 방식
    // @RequestBody 어노테이션으로 받은 값은 Json 형식이기 때문에 Entity 클래스에 선언된 변수와 매칭시켜준다.
    // # 여기서는 왜 HttpStatus.CREATED를 사용하는 것일까?
    @PostMapping("/boards")
    public ResponseEntity<?> save(@RequestBody BoardEntity board){
        return new ResponseEntity<>(boardService.save(board), HttpStatus.CREATED);
    }

    @PutMapping("/boards/{id}")
    public ResponseEntity<?> editBoard(@PathVariable("id") Long id, @RequestBody BoardEntity board){
        return new ResponseEntity<>(boardService.editBoard(id, board), HttpStatus.OK);
    }

    @DeleteMapping("/boards/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable("id") Long id){
        boardService.deleteBoard(id);
        return new ResponseEntity<>("삭제 완료", HttpStatus.OK);
    }
}
