package com.dongeunpaeng.simplerestapp.service.posts;

import com.dongeunpaeng.simplerestapp.domain.posts.PostStatus;
import com.dongeunpaeng.simplerestapp.domain.posts.Posts;
import com.dongeunpaeng.simplerestapp.domain.posts.PostsRepository;
import com.dongeunpaeng.simplerestapp.web.dto.PostDto;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional(readOnly = true)
    public List<PostDto> getPosts() {
        return postsRepository.findAll().stream().map(PostDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PostDto getPost(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("없는 ID입니다."));
        return PostDto.builder().entity(entity).build();
    }

    @Transactional(readOnly = true)
    public List<PostDto> getDrafts() {
        return postsRepository.findAllByStatusGreaterThan(PostStatus.PUBLIC.numeric).stream().map(PostDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long savePost(PostDto saveDto) {
        return postsRepository.save(saveDto.toEntity()).getId();
    }

    @Transactional
    public Long editPost(PostDto editDto) {
        postsRepository.findById(editDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("없는 ID입니다."));
        return postsRepository.save(editDto.toEntity()).getId();
    }

    @Transactional
    public void deletePost(PostDto deleteDto) {
        postsRepository.findById(deleteDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("없는 ID입니다."));
        postsRepository.deleteById(deleteDto.getId());
    }
}
