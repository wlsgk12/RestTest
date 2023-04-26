package org.koreait.models.board;

import lombok.RequiredArgsConstructor;
import org.koreait.entities.BoardData;
import org.koreait.repositories.BoardDataRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * 게시글 번호로 게시글 조회
 *
 */
@Service
@RequiredArgsConstructor
public class BoardInfoService {
    private final BoardDataRepository repository;

    public BoardData get(Long id) {

        BoardData data = repository.findById(id).orElseThrow(()->new CommonRestException("게시글이 존재하지 않습니다.", HttpStatus.BAD_REQUEST));
        return data;
    }
}
