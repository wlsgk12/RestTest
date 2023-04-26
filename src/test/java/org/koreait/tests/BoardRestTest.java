package org.koreait.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.koreait.controllers.BoardForm;
import org.koreait.entities.BoardData;
import org.koreait.models.board.BoardDeleteService;
import org.koreait.models.board.BoardInfoService;
import org.koreait.models.board.BoardListService;
import org.koreait.models.board.BoardSaveService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
public class BoardRestTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BoardSaveService saveService;
    @Autowired
    private BoardListService listService;
    @Autowired
    private BoardInfoService infoService;
    @Autowired
    private BoardDeleteService deleteService;
    private BoardForm boardForm;

    @BeforeEach
    public void init(){
          boardForm= boardForm.builder()
                .subject("제목")
                .content("내용")
                .build();
    }
    @Test
    @DisplayName("게시글 추가 성공시 응답코드 200")
    public void insertTest() throws Exception {
        String a = String.format("{ \"subject\":\"%s\", \"content\":\"%s\"}",boardForm.getSubject(),boardForm.getContent());

        mockMvc.perform(post("/api/board/insert")
                .content(a)
                .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("게시글 전체 조회 성공시 응답코드 200")
    public void getsTest() throws Exception {

        mockMvc.perform(get("/api/board/gets")
                .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("게시글 id 조회 성공시 응답코드 200")
    public void getTest() throws Exception {
        saveService.save(boardForm);

        String a = String.format("{\"id\":\"%d\", \"subject\":\"%s\", \"content\":\"%s\"}",boardForm.getId(),boardForm.getSubject(),boardForm.getContent());
        mockMvc.perform(get("/api/board/get/"+boardForm.getId())
                        .content(a)
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("게시글 삭제 성공시 응답코드 200")
    public void deleteTest() throws Exception {
        saveService.save(boardForm);

        String a = String.format("{\"id\":\"%d\", \"subject\":\"%s\", \"content\":\"%s\"}",boardForm.getId(),boardForm.getSubject(),boardForm.getContent());
        mockMvc.perform(get("/api/board/delete/"+boardForm.getId())
                        .content(a)
                        .contentType("application/json"))
                .andExpect(status().isOk());



    }

}
