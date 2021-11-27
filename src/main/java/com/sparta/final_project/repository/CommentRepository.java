package com.sparta.final_project.repository;

import com.sparta.final_project.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 댓글에 사용할 Repository
    // 게시글에 해당하는 댓글을 모두 불러오고, 생성 날짜를 기준으로 내림차순 정렬
    List<Comment> findAllByContentNoOrderByCreatedAtDesc(Long contentNo);
}
