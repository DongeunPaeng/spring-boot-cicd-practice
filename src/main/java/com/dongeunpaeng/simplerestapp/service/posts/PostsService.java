package com.dongeunpaeng.simplerestapp.service.posts;

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
    public List<PostDto> findPosts() {
        return postsRepository.findAll().stream().map(PostDto::new).collect(Collectors.toList());
    }
}
