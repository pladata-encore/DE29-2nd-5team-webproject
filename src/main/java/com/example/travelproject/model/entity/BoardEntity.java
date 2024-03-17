package com.example.travelproject.model.entity;

import java.util.List;

import org.hibernate.annotations.ColumnDefault;

import com.example.travelproject.config.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// @ToString
@Entity(name = "BoardEntity")
@Table(name = "board")
public class BoardEntity extends BaseEntity{

    // private UserEntity userEntity; 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Long noticeId; 

    // 작성자
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties
    private UserEntity user; 

    @Column(nullable = false)
    private String title; 
    private String contents;

    @ColumnDefault("0")
    private int viewCnt;

    @JsonIgnoreProperties
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentEntity> commentList;
}
