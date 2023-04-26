package org.koreait.restControllers;

import org.koreait.commons.rest.JSONResult;
import org.koreait.controllers.BoardForm;
import org.koreait.entities.BoardData;
import org.koreait.entities.QBoardData;
import org.koreait.models.board.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/board")
public class RestBoardController {

    @Autowired
    private BoardSaveService saveService;
    @Autowired
    private BoardListService listService;
    @Autowired
    private BoardInfoService infoService;
    @Autowired
    private BoardDeleteService deleteService;

    @PostMapping("/insert")
    public ResponseEntity<JSONResult<BoardForm>> insert(@RequestBody BoardForm boardForm){

        String subject = boardForm.getSubject();
        String content = boardForm.getContent();
        if(subject == null || subject.isBlank()){
            throw new CommonRestException("제목을 작성해주세요", HttpStatus.BAD_REQUEST);
        }
        if(content == null || content.isBlank()){
            throw new CommonRestException("내용을 작성해주세요",HttpStatus.BAD_REQUEST);
        }

        saveService.save(boardForm);

        return ResponseEntity.ok().build();
    }
    @GetMapping("/gets")
    public ResponseEntity<List<BoardData>> gets(){
        List<BoardData> boardDatas = listService.gets();

        return ResponseEntity.ok().body(boardDatas);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<BoardData> get(@PathVariable Long id){
        BoardData boardData = infoService.get(id);

        return ResponseEntity.ok().body(boardData);
    }
    @GetMapping("/delete/{id}")
    public ResponseEntity<JSONResult<BoardData>> delete(@PathVariable Long id){
        JSONResult<BoardData> jsonResult = new JSONResult<>();
         deleteService.delete(id);
         jsonResult.setSuccess(true);
         jsonResult.setStatus(HttpStatus.OK);
         jsonResult.setMessage("삭제 성공");
        return ResponseEntity.ok(jsonResult);
    }

}
