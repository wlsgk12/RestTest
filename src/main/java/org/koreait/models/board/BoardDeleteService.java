package org.koreait.models.board;

import lombok.RequiredArgsConstructor;
import org.koreait.entities.BoardData;
import org.koreait.repositories.BoardDataRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardDeleteService {
    private final BoardDataRepository repository;

    public void delete(Long id) {
        BoardData boardData = repository.findById(id).orElseThrow(()->new CommonRestException("게시글이 존재하지 않습니다.", HttpStatus.BAD_REQUEST));

        repository.delete(boardData);
        repository.flush();
    }
}
