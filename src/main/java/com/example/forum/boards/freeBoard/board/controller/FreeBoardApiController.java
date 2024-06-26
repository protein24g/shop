package com.example.forum.boards.freeBoard.board.controller;

import com.example.forum.boards.freeBoard.board.dto.request.FreeBoardRequest;
import com.example.forum.boards.freeBoard.board.dto.request.FreeBoardSearch;
import com.example.forum.boards.freeBoard.board.dto.response.FreeBoardResponse;
import com.example.forum.boards.freeBoard.board.service.FreeBoardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * 자유 게시판 API 컨트롤러
 */
@RestController
@RequiredArgsConstructor
public class FreeBoardApiController {
    private final FreeBoardServiceImpl freeBoardServiceImpl;

    /**
     * 게시글 목록을 검색하여 반환
     *
     * @param dto 검색 조건을 포함한 DTO
     * @return 검색된 게시글 목록 페이지
     */
    @PostMapping("/api/freeBoard")
    public ResponseEntity<?> boardPage(@RequestBody FreeBoardSearch dto) {
        Page<FreeBoardResponse> freeBoardResponses = freeBoardServiceImpl.boardPage(dto); // 검색 페이징
        return ResponseEntity.status(HttpStatus.OK).body(freeBoardResponses);
    }

    /**
     * 특정 게시글의 상세 정보 반환
     *
     * @param boardId 게시글 ID
     * @return 게시글 상세 정보
     */
    @GetMapping("/api/freeBoard/{boardId}")
    public ResponseEntity<?> getBoardDetail(@PathVariable("boardId") Long boardId){
        try {
            FreeBoardResponse freeBoardResponse = freeBoardServiceImpl.getDetail(boardId);
            return ResponseEntity.status(HttpStatus.OK).body(freeBoardResponse);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * 게시글 수정을 위한 데이터 반환
     *
     * @param boardId 게시글 ID
     * @return 게시글 수정을 위한 데이터
     */
    @GetMapping("/api/freeBoard/{boardId}/update")
    public ResponseEntity<?> getBoardUpdateData(@PathVariable(name = "boardId") Long boardId){
        try{
            FreeBoardResponse freeBoardResponse = freeBoardServiceImpl.getBoardUpdateData(boardId);
            return ResponseEntity.status(HttpStatus.OK).body(freeBoardResponse);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * 게시글 수정
     *
     * @param boardId 게시글 ID
     * @param dto     수정할 게시글 내용을 담은 DTO
     * @return 수정 결과 메시지
     */
    @PutMapping("/api/freeBoard/{boardId}")
    public ResponseEntity<?> updateBoard(@PathVariable("boardId") Long boardId, FreeBoardRequest dto) {
        try {
            freeBoardServiceImpl.update(boardId, dto);
            return ResponseEntity.status(HttpStatus.OK).body("글 수정완료");
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * 게시글 삭제
     *
     * @param boardId 게시글 ID
     */
    @DeleteMapping("/api/freeBoard/{boardId}")
    public ResponseEntity<?> delete(@PathVariable("boardId") Long boardId) {
        try {
            freeBoardServiceImpl.delete(boardId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * 특정 게시물 좋아요
     *
     * @param boardId
     * @return
     */
    @PostMapping("/api/freeBoard/{boardId}/like")
    public ResponseEntity<?> insertBoardLike(@PathVariable(name = "boardId") Long boardId){
        return null;
    }

    /**
     * 특정 게시물 좋아요
     *
     * @param boardId
     * @return
     */
    @DeleteMapping("/api/freeBoard/{boardId}/like")
    public ResponseEntity<?> deleteBoardLike(@PathVariable(name = "boardId") Long boardId){
        return null;
    }
}
