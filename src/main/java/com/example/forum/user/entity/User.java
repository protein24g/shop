    package com.example.forum.user.entity;

    import com.example.forum.boards.freeBoard.board.entity.FreeBoard;
    import com.example.forum.boards.freeBoard.comment.entity.FreeBoardComment;
    import jakarta.persistence.*;
    import lombok.*;

    import java.time.LocalDateTime;
    import java.util.ArrayList;
    import java.util.List;

    @Entity
    @Getter
    @RequiredArgsConstructor
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

        public enum Role{ADMIN, USER}
        @Enumerated(EnumType.STRING) // 권한을 문자열로 db에 저장
        private Role role;

        @Column(name = "is_active")
        private boolean isActive = true; // 사용자의 활성화 상태를 나타냅니다.

        // CascadeType.REMOVE : 부모 Entity 삭제시 자식 Entity 들도 삭제
        // orphanRemoval = true : 부모 엔티티와의 관계가 끊어진 자식 엔티티들을 자동으로 삭제
        @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
        private List<FreeBoard> freeBoards = new ArrayList<>();

        @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
        private List<FreeBoardComment> freeBoardComments = new ArrayList<>();

        @Builder
        public User(String nickname, String loginId, String loginPw, LocalDateTime createDate,
                    int age, User.Gender gender, User.Role role, boolean isActive){
            this.nickname = nickname;
            this.loginId = loginId;
            this.loginPw = loginPw;
            this.createDate = createDate;
            this.age = age;
            this.gender = gender;
            this.role = role;
            this.isActive = isActive;
        }

        public void addBoard(FreeBoard freeBoard){
            this.freeBoards.add(freeBoard);
            freeBoard.setUser(this);
        }

        public void addComment(FreeBoardComment freeBoardComment){
            this.freeBoardComments.add(freeBoardComment);
            freeBoardComment.setUser(this);
        }

        public boolean getActive() {
            return this.isActive;
        }

        public void setActive(boolean active) {
            this.isActive = active;
        }
    }
