package com.sparta.final_project.repository;

import com.sparta.final_project.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long> {
    // 게시글에 사용할 Repository
    // 모든 게시글을 불러오고, 게시글 생성일을 기준으으 내림차순 정렬
    List<Content> findAllByOrderByCreatedAtDesc();
}