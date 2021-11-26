package com.sparta.final_project.repository;

import com.sparta.final_project.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByContentNoOrderByCreatedAtDesc(Long contentNo);
}
