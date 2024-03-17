package com.example.travelproject.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
    private String userId;
    private String userPw;
    private String userEmail;
    private String userNm;
    private String userSex;
    private String userPhNmb;
    private String role;
    private Boolean isLogin;
}
