package com.example.mentoring.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;


@JsonInclude(JsonInclude.Include.NON_NULL) // null 값을 가지는 필드는, Json 응답에 포함되지 않는다.
@AllArgsConstructor
@Getter
public class Success<T> implements Result {
    // 클래스가 T일 경우 어떤 데이터 값이든 가져올 수 있다.
    private T data;
}
