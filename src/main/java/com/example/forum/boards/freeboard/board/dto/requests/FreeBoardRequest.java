package com.example.forum.boards.freeboard.board.dto.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@ToString
public class FreeBoardRequest {
    private String title;

    private String content;

    private List<MultipartFile> images;
}
