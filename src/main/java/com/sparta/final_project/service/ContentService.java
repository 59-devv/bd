package com.sparta.final_project.service;

import com.sparta.final_project.model.Content;
import com.sparta.final_project.repository.ContentRepository;
import com.sparta.final_project.dto.ContentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ContentService {

    private final ContentRepository contentRepository;

    // 게시글 수정 기능
    // 게시글이 수정될 때 DB에 잘 반영될 수 있도록 Transactional 부여
    @Transactional
    public Long update(Long id, ContentRequestDto requestDto) {
        Content content = contentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("메모가 없습니다.")
        );
        content.update(requestDto);
        return content.getId();
    }
}
