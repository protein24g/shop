package com.example.shop.board.qnaboard.controller;

import com.example.shop.board.qnaboard.dto.requests.QnaRequest;
import com.example.shop.board.qnaboard.dto.response.QnaResponse;
import com.example.shop.board.qnaboard.service.QnaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class QnaController {
    private final QnaService qnaService;

    // C(Create)
    @GetMapping("/qna/create")
    public String createP(){
        return "qnaboard/create";
    }

    @PostMapping("/qna/create")
    public String create(QnaRequest dto, Model model){
        try{
            QnaResponse qnaResponse = qnaService.create(dto);
            model.addAttribute("msg", "글 작성이 완료되었습니다.");
            model.addAttribute("url", "/qna/" + qnaResponse.getId());
            return "message/main";
        } catch (IllegalArgumentException e){
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("url", "/login");
            return "message/main";
        }
    }

    // R(Read)
    @GetMapping("/qna")
    public ResponseEntity<?> qnaP(Model model,
                               @RequestParam(value = "keyword", required = false) String keyword,
                               @RequestParam(value = "page", defaultValue = "0") int page,
                               @RequestParam(value = "option", required = false) String option) {
        Page<QnaResponse> qnaResponse;

        if (keyword != null && !keyword.isEmpty()) { // 키워드가 있으면
            qnaResponse = qnaService.page(keyword, page, option); // 검색 페이징
        } else {
            qnaResponse = qnaService.page("", page, ""); // 기본 리스트 페이징
        }

        return ResponseEntity.status(HttpStatus.OK).body(qnaResponse);
    }

    @GetMapping("/qna/{boardNum}")
    public String qnaDetailP(@PathVariable("boardNum") Long boardNum, Model model){
        try {
            QnaResponse qnaResponse = qnaService.readDetail(boardNum);
            model.addAttribute("qna", qnaResponse);
            model.addAttribute("comments", qnaResponse.getCommentResponses());
        }catch (IllegalArgumentException e){
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("url", "/qna");
            return "message/main";
        }
        return "qnaboard/detail";
    }
}
