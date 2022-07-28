package com.example.mentoring.controller;

import com.example.mentoring.dto.board.BoardCreateRequestDto;
import com.example.mentoring.dto.board.BoardEditRequestDto;
import com.example.mentoring.service.BoardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class BoardControllerTest {
    @InjectMocks
    BoardController boardController;

    @Mock // 가짜 객체 ( 더미 파일 ^^ ) -> 데베에 넣으면 안돼서? 그럼 ㅎ
    BoardService boardService;

    MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void beforeEach(){
        mockMvc = MockMvcBuilders.standaloneSetup(boardController).build();
    }

    @Test
    @DisplayName("게시글 작성")
    public void saveBoardTest() throws Exception{
        // given
        BoardCreateRequestDto boardRequestDto = new BoardCreateRequestDto("제목", "내용", "홍길동");

        // when, then
        mockMvc.perform(
                post("/api/boards")
                        .contentType(MediaType.APPLICATION_JSON) // 입력받는 파라미터의 타입 지정 Json으로 설정?
                        .content(objectMapper.writeValueAsString(boardRequestDto))) // 데이터베이스에 String으로 저장?
                .andExpect(status().isCreated()); // Http 상태를 넘겨줌 isCreated -> CREATED

        verify(boardService).save(boardRequestDto);
    }

    @Test
    @DisplayName("전체 게시글 조회")
    public void findBoardsTest() throws Exception{
        // given

        // when, then
        mockMvc.perform(
                get("/api/boards")) // url 입력 -> 127.0.0.1:8080/boards
                .andExpect(status().isOk()); // Http 상태를 넘겨줌 isOk -> OK

        verify(boardService).getBoards();
    }

    @Test
    @DisplayName("게시물 단건 조회")
    public void findBoardTest() throws Exception{
        // given
        Long id = 1L;

        // when, then
        mockMvc.perform(
                get("/api/boards/{id}", id))
                .andExpect(status().isOk());

        verify(boardService).getBoard(id);
    }

    @Test
    @DisplayName("게시글 수정")
    public void editBoardTest() throws Exception{
        // given
        Long id = 1L;
        BoardEditRequestDto boardEditRequestDto = new BoardEditRequestDto("제목1", "내용1");

        // when, then
        mockMvc.perform(
                put("/api/boards/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(boardEditRequestDto)))
                .andExpect(status().isOk());
        verify(boardService).editBoard(id, boardEditRequestDto);
        assertThat(boardEditRequestDto.getTitle()).isEqualTo("제목1");
    }

    @Test
    @DisplayName("게시글 삭제")
    public void deleteBoardTest() throws Exception{
        // given
        Long id = 1L;

        // when, then
        mockMvc.perform(
                delete("/api/boards/{id}", id))
                .andExpect(status().isOk());
        verify(boardService).deleteBoard(id);
    }

}
