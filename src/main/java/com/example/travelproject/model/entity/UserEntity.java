package com.example.travelproject.model.entity;

import java.util.List;

import com.example.travelproject.config.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
@Entity(name = "UserEntity")
@Table(name = "user")
public class UserEntity extends BaseEntity{
    @Id // 기본키: 유니크
    @NotBlank
    @Pattern(regexp="^[a-zA-Z\\d]*$")
    private String userId;
    @NotBlank
    private String userPw;
    @Column(unique = true)
    @NotBlank
    @Email
    private String userEmail;
    @NotBlank
    private String userNm;
    private String userSex;

    @NotBlank
    @Pattern(regexp = "^01(?:0|1|[6-9])-?\\d{3,4}-?\\d{4}$", message = "올바른 전화번호를 입력해주세요.")
    private String userPhNmb;
    // 일반사용자 / 관리자를 구분용
    private String role;
    // 로그인 유무
    @Column(columnDefinition = "tinyint(1) default 0")
    private Boolean isLogin;

    @JsonIgnoreProperties
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardEntity> boardList;
}