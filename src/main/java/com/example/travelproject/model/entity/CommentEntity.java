package com.example.travelproject.model.entity;

import com.example.travelproject.config.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "CommentEntity")
@Table(name = "comment")
public class CommentEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    
    @ManyToOne
    @JoinColumn(name = "notice_id")
    private BoardEntity board; // 게시판 엔티티에 대한 참조

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user; // 사용자 엔티티에 대한 참조

    @Column(nullable = false)
    private String contents;

}
