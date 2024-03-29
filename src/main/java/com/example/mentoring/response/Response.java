package com.example.mentoring.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
/*
* Response -> Success와 Failure의 두 가지를 제어한다. */
public class Response {
    // 대분류에 들어가는 이름이다.
    private boolean success;
    private int code;
    private Result result;

    public static Response success(){
        // 데이터가 없이 성공 반환해주는 경우
        return new Response(true, 0, null);
    }

    public static <T> Response success(T data){
        // 데이터를 포함해서 성공을 반환해주는 경우
        return new Response(true, 0, new Success<>(data));
    }

    public static Response failure(int code, String msg){
        // 에러 발생시 반환해주는 경우
        return new Response(false, code, new Failure(msg));
    }
}

/*
* {
    "success": true,
    "code": 0,
    "result": {
        "data": {
            "id": 13,
            "title": "Rest Api",
            "content": "Rest Api content",
            "writer": "admin"
        }
    }
}
* */
