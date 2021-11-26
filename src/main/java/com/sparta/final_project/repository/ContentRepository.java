package com.sparta.final_project.repository;

import com.sparta.final_project.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long> {
    List<Content> findAllByOrderByCreatedAtDesc();
}
