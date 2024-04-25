package com.example.shop.user.entity;

import com.example.shop.comment.entity.Comment;
import com.example.shop.qnaboard.entity.QuestionAndAnswer;
import com.example.shop.reviewboard.entity.Review;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
@Transactional
@ToString(exclude = {"questionAndAnswers"}) // 순환 참조를 피하기 위해 ToString 에서 제외
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nickname", unique = true, nullable = false)
    private String nickname;

    @Column(name = "login_id", unique = true, nullable = false)
    private String loginId;

    @Column(name = "login_pw", nullable = false)
    private String loginPw;

    @Column(name = "create_date", updatable = false)
    private LocalDateTime createDate;

    @Column(name = "age", nullable = false)
    private int age;

    public enum Gender{MALE, FEMALE}
    @Enumerated(EnumType.STRING) // 성별을 문자열로 db에 저장
    private Gender gender;

    @Column(name = "address", nullable = false)
    private String address;

    public enum Role{ADMIN, USER}
    @Enumerated(EnumType.STRING) // 권한을 문자열로 db에 저장
    private Role role;

    // CascadeType.REMOVE : 부모 Entity 삭제시 자식 Entity 들도 삭제
    // orphanRemoval = true : 부모 엔티티와의 관계가 끊어진 자식 엔티티들을 자동으로 삭제
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<QuestionAndAnswer> questionAndAnswers = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @Builder
    public User(String nickname, String loginId, String loginPw, LocalDateTime createDate,
                   int age, User.Gender gender, String address, User.Role role){
        this.nickname = nickname;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.createDate = createDate;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.role = role;
    }

    public void addQuestionAndAnswer(QuestionAndAnswer questionAndAnswer) {
        this.questionAndAnswers.add(questionAndAnswer);
        questionAndAnswer.setUser(this);
    }

    public void addComments(Comment comment){
        this.comments.add(comment);
        comment.setUser(this);
    }

    public void addReview(Review review){
        this.reviews.add(review);
        review.setUser(this);
    }
}
