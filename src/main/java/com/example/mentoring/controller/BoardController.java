package com.example.mentoring.controller;

import com.example.mentoring.dto.board.BoardCreateRequestDto;

import com.example.mentoring.dto.board.BoardEditRequestDto;
import com.example.mentoring.response.Response;
import com.example.mentoring.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/*
* GetMapping : 데이터베이스에서 데이터를 가져와 사용할 경우 (id값 필요할 수도 있고 없을 수도 있음)
* PostMapping : 데이터베이스에 데이터를 저장해야 하는 경우
* PutMapping : 데이터베이스의 데이터 내용을 수정해야 하는 경우 (id값 필요)
* DeleteMapping : 데이터베이스의 데이터를 지울때 사용 (id값 필요)
*
* 질문 # = 1*/

// ResponseEntity<>(출력(return), http통신 수신여부)로 입력하기 때문에 출력값에 변화를 주고 싶다면 출력부분을 수정해야한다.

@RestController // Controller -> 진행할 경우 MVC 패턴이 아니기 때문에 서버오류가 발생한다.
@RequiredArgsConstructor // @Non null + static 생성자
@RequestMapping("/api") // 소켓주소 다음 맵핑주소로 해당 BoardController class가 다 맵핑처리 된다.
public class BoardController {
    private final BoardService boardService;

    // Get 방식
    // 데이터 전체 조회
    @GetMapping("/boards")
    @ResponseStatus(HttpStatus.OK) // 이거를 써주는 이유는 Httpstatus를 controller에서 안넘겨주기 위해
    public Response getBoards(){
        return Response.success(boardService.getBoards());
    }

    // Get 방식
    // 데이터 단일 조회 (id 값 필요)
    // id 값이 쓰인다면 파라미터에 (@PathVariable("아이디") Type 변수)로 작성
    @GetMapping("/boards/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response getBoard(@PathVariable("id") Long id){
        return Response.success(boardService.getBoard(id));
    }

    // Post 방식
    // @RequestBody 어노테이션으로 받은 값은 Json 형식이기 때문에 Entity 클래스에 선언된 변수와 매칭시켜준다.
    // # 여기서는 왜 HttpStatus.CREATED를 사용하는 것일까?
    // @Valid -> Validation을 통해서 유효성 검사를 진행 위한 어노테이션 ( gradle 추가 )
    @PostMapping("/boards")
    @ResponseStatus(HttpStatus.CREATED)
    public Response save(@RequestBody @Valid BoardCreateRequestDto boardRequestDto){
        return Response.success(boardService.save(boardRequestDto));
    }

    @PutMapping("/boards/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response editBoard(@PathVariable("id") Long id, @RequestBody @Valid BoardEditRequestDto boardEditRequestDto){
        return Response.success(boardService.editBoard(id, boardEditRequestDto));
    }

    @DeleteMapping("/boards/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteBoard(@PathVariable("id") Long id){
        boardService.deleteBoard(id);
        return Response.success("삭제 완료");
    }

    @DeleteMapping("/boards")
    @ResponseStatus(HttpStatus.OK)
    public Response allDeleteBoard(){
        boardService.allDeleteBoard();
        return Response.success("전체 삭제 완료");
    }
}
