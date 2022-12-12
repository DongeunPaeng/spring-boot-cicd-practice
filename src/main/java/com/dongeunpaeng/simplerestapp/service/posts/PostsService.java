package com.dongeunpaeng.simplerestapp.service.posts;

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

    @Transactional
    public List<PostDto> getDrafts() {
        return postsRepository.findAllByStatusGreaterThan(0L).stream().map(PostDto::new).collect(Collectors.toList());
    }

    @Transactional
    public Long savePost(PostDto saveDto) {
        Posts returnedPost = postsRepository.save(saveDto.toEntity());
        System.out.println(returnedPost.getTitle());
        return returnedPost.getId();
    }
}
