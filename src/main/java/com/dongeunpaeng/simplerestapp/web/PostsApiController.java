package com.dongeunpaeng.simplerestapp.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public List<PostDto> getPosts() {
        return postsService.getPosts();
    }

    @GetMapping("/api/v1/post/{id}")
    public PostDto getPost(@PathVariable Long id) {
        return postsService.getPost(id);
    }

    // TODO: need token
    @GetMapping("/api/v1/post/draft")
    public List<PostDto> getDrafts() {
        return postsService.getDrafts();
    }

    // TODO: need token
    @GetMapping("/api/v1/post/draft/{id}")
    public PostDto getDraft(@PathVariable Long id) {
        return postsService.getPost(id);
    }

    // TODO: need token
    @PostMapping("/api/v1/post/write")
    public Long savePost(@RequestBody PostDto postDto) {
        return postsService.savePost(postDto);
    }

    // TODO: need token
    @PostMapping("/api/v1/post/edit")
    public Long editPost(@RequestBody PostDto postDto) {
        return postsService.editPost(postDto);
    }

    // // TODO: need token
    // @PostMapping("/api/v1/post/delete")
    // public Long deletePost(@RequestBody PostDto postDto) {
    // return postsService.deletePost(postDto);
    // }
}
