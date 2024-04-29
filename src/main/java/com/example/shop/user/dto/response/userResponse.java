package com.example.shop.user.dto.response;

import com.example.shop.board.comment.dto.response.CommentResponse;
import com.example.shop.board.reviewboard.dto.response.ReviewResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class userResponse {
    private String nickname;
    private LocalDateTime createDate;
    private boolean isActive;
    private Page<ReviewResponse> reviews;
    private Page<CommentResponse> comments;

    @Builder
    public userResponse(String nickname, LocalDateTime createDate, boolean isActive,
                        Page<ReviewResponse> reviews, Page<CommentResponse> comments){
        this.nickname = nickname;
        this.createDate = createDate;
        this.isActive = isActive;
        this.reviews = reviews;
        this.comments = comments;
    }
}
