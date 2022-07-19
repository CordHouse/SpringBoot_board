package com.example.mentoring.repository;

import com.example.mentoring.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

// 데이터베이스와 직접적으로 연동되는 Repository는 Jpa를 통해 해당 Entity class와 primary key의 자료형을 넘겨준다.
// JpaRepository 안에 함수들이 정리되어 있어 수정할 필요는 없어보인다.
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
}
