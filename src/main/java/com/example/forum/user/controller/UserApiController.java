package com.example.forum.user.controller;

import com.example.forum.boards.freeBoard.board.dto.response.FreeBoardResponse;
import com.example.forum.user.dto.requests.JoinRequest;
import com.example.forum.user.dto.response.UserResponse;
import com.example.forum.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 유저 API 컨트롤러
 */
@RestController
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;

    // C(Create)

    /**
     * 회원가입 처리
     *
     * @param dto JoinRequest 객체
     * @return HTTP 상태 및 메시지 반환
     */
    @PostMapping("/api/joinProc")
    public ResponseEntity<?> joinProc(@RequestBody JoinRequest dto) {
        try {
            userService.join(dto);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // R(Read)

    /**
     * 사용자 정보 조회
     *
     * @return 사용자 정보 반환
     */
    @GetMapping("/api/userInfo")
    public ResponseEntity<?> getUserInfo(){
        try{
            UserResponse userResponse = userService.getUserInfo();
            return ResponseEntity.status(HttpStatus.OK).body(userResponse);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * 마이페이지 게시글 조회
     *
     * @param id   사용자 ID
     * @param page 페이지 번호
     * @return 게시글 목록 반환
     */
    @GetMapping("/api/mypage/boards")
    public ResponseEntity<?> myPageBoards(@RequestParam(name = "id") String id,
                                          @RequestParam(name = "page", defaultValue = "0") int page){
        try{
            Page<FreeBoardResponse> boardResponses = userService.myPageBoards(id, page);
            return ResponseEntity.status(HttpStatus.OK).body(boardResponses);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
