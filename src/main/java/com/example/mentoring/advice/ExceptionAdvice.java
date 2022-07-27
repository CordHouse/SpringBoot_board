package com.example.mentoring.advice;

import com.example.mentoring.exception.BoardNotFoundException;
import com.example.mentoring.exception.IdNotFoundException;
import com.example.mentoring.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(BoardNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response boardNotFoundException(){
        return Response.failure(404, "게시글을 찾을 수 없습니다.");
    }

    @ExceptionHandler(IdNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response idNotFoundException(){
        return Response.failure(404, "해당 게시글이 존재하지 않습니다.");
    }

    // 400 에러
    // 요청 객체의 validation을 수행할 때, MethodArgumentNotValidException이 발생
    // 각 검증(DTO) 어노테이션 별로 지정해놨던 메시지를 응답
    // DTO를 통해 controller에서 검증을 진행 후 error발생시 exception처리를 해주어야함.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response methodArgumentNotValidException(MethodArgumentNotValidException e) { // 2
        return Response.failure(400, e.getBindingResult().getFieldError().getDefaultMessage());
    }
}
