package com.example.travelproject.service;

import com.example.travelproject.model.dto.UserDto;
import com.example.travelproject.model.entity.UserEntity;

public interface UserService {

    // public UserDto findByUserId(String userId);

    public void deleteUser(String userId);

    // 로그인 성공시 >> 로그인 유무 저장
    public void updateIsLoginById(String id, Boolean isLogin);

    public void joinUserDto(UserEntity dto);

    public void updateUserDto(UserEntity dto);

    public String findUserIdByEmail(String userNm, String userEmail);

    public UserDto findByUserId(String username);

}
