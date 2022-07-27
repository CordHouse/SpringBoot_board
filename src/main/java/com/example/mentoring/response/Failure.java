package com.example.mentoring.response;

import lombok.AllArgsConstructor;
import lombok.Getter;


// 여기에 JsonInclude를 안써줘도 되는 이유는 msg는 null값이 존재할 수 없기 때문이다.
// 실패 이유를 에러로 리턴해줘야 하기 때문임.

@AllArgsConstructor
@Getter
public class Failure implements Result{
    private String msg;
}
