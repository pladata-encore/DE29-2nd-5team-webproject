package com.example.travelproject.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.travelproject.model.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.transaction.Transactional;



public interface UserRepository extends JpaRepository<UserEntity, String> {
    @JsonIgnoreProperties
    @Transactional
    @Query(value = "select * from user where user_id = :user_id", nativeQuery = true)
    public UserEntity getUserDtoById(@Param(value = "user_id") String userId);

    @Query(value = "select * from user where user_nm = :user_nm and user_email = :user_email", nativeQuery = true)
    public UserEntity getUserIdByEmail(
            @Param(value = "user_nm") String userNm,
            @Param(value = "user_email") String userEmail);

    // 이메일 중복 조회
    public UserEntity findByUserEmail(String userEmail);

    @Query(value = "select * from user", nativeQuery = true)
    public List<UserEntity> findAllUser();

    @Query(value = "select * from user where user_id = :user_id", nativeQuery = true)
    public UserEntity findOneUser(@Param(value = "user_id") String user_id);

    @Query(value = "select user_id from user where user_id = :user_id", nativeQuery = true)
    public String findByUserId(@Param(value = "user_id") String userId);
}
