package com.example.forum.boards.imageBoard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ImageBoardController {
    // R(Read)
    @GetMapping("/imageBoards")
    public String freeBoardsP(){
        return "boards/imageBoard/imageBoardList";
    }
}
