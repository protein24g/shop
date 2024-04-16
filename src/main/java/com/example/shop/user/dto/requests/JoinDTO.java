package com.example.shop.user.dto.requests;

import com.example.shop.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
public class JoinDTO {
    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    @Size(min = 2, max = 12, message = "닉네임은 2자 이상 12자 이하로 입력하세요.")
    private String nickname;

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    @Size(min = 6, message = "아이디는 최소 6자 이상 입력하세요.")
    private String userId;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Size(min = 6, message = "비밀번호는 최소 6자 이상 입력하세요.")
    private String userPw;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Size(min = 6, message = "비밀번호는 최소 6자 이상 입력하세요.")
    private String userPwCheck;

    private LocalDateTime createDate;

    private int age;

    private User.Gender gender;

    @NotBlank(message = "주소는 필수 입력 값입니다.")
    private String address;

    private User.Role role;
}
