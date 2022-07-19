package com.example.mentoring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data // Getter + Setter 자동 생성
@NoArgsConstructor // 빈 생성자
@AllArgsConstructor // 모든 인스턴스를 받는 생성자
@Entity // Board가 Entity인 것을 명시 ( 컴퓨터가 알 수 있도록 )
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 인스턴스 생성시 자동으로 인덱스를 붙인다. 1 -> 2 -> 3 ...
    // 데이터베이스에서 구별하기 위한 Id 값으로 @GeneratedValue를 통해 AI(Auto Increase) 설정한다.
    private Long id;

    private String title;

    private String content;

    private String writer;

}
