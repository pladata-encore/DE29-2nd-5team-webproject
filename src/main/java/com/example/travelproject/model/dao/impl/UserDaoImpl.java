package com.example.travelproject.model.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.travelproject.model.dao.UserDao;
import com.example.travelproject.model.entity.UserEntity;
import com.example.travelproject.model.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@SuppressWarnings("null")
public class UserDaoImpl implements UserDao{
        
    @Autowired
    private UserRepository userRepository;

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    public UserEntity findByUserId(String userId) {
        return userRepository.getUserDtoById(userId);
    }

    public void insertUser(UserEntity entity) {
        userRepository.save(entity);
    }

    public void updateUser(UserEntity entity) {
        userRepository.save(entity);
    }

    public UserEntity findByUserEmail(String userNm, String userEmail) {
        return userRepository.getUserIdByEmail(userNm, userEmail);
    }

    // [이메일 중복 조회] by 성민
    @Override
    public UserEntity findByUserEmail(String userEmail) {
        log.info("[UserDaoImpl][findByUserEmail] >>> " + userRepository.findByUserEmail(userEmail));
        return userRepository.findByUserEmail(userEmail);
    }

}
