package com.dongeunpaeng.simplerestapp.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dongeunpaeng.simplerestapp.service.posts.PostsService;
import com.dongeunpaeng.simplerestapp.web.dto.PostDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @GetMapping("/api/v1/post")
    public List<PostDto> findPosts() {
        return postsService.findPosts();
    }

    @PostMapping("/api/v1/post/write")
    public Long savePost(@RequestBody PostDto postDto) {
        return postsService.savePost(postDto);
    }
}
