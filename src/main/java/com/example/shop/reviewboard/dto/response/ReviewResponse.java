package com.example.shop.reviewboard.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ReviewResponse {
    private Long id;
    private String nickname;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private Double reviewScore;

    @Builder ReviewResponse(Long id, String nickname, String title, String content, LocalDateTime createDate, Double reviewScore){
        this.id = id;
        this.nickname = nickname;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.reviewScore = reviewScore;
    }
}
