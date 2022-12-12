package com.dongeunpaeng.simplerestapp.service.posts;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.dongeunpaeng.simplerestapp.domain.posts.Posts;
import com.dongeunpaeng.simplerestapp.domain.posts.PostsRepository;
import com.dongeunpaeng.simplerestapp.web.dto.PostDto;

@ExtendWith(MockitoExtension.class)
class PostsServiceTest {
    @Mock
    private PostsRepository postsRepository;

    @InjectMocks
    private PostsService postsService;

    @Test
    public void savePost() {
        // given
        Posts posts = Posts.builder()
                .author(1L)
                .title("test title")
                .post("test post")
                .status(0L)
                .type(0L)
                .build();
        PostDto saveDto = PostDto.builder()
                .entity(posts)
                .build();
        when(postsRepository.save(any())).thenReturn(posts);
        ReflectionTestUtils.setField(posts, "id", 123L);
        // when
        Long returnedId = postsService.savePost(saveDto);
        // then
        assertEquals(returnedId, 123L);
    }

    @Test
    public void findPosts() {
        // given
        Posts posts = Posts.builder()
                .author(1L)
                .title("test title")
                .post("test post")
                .status(0L)
                .type(0L)
                .build();
        when(postsRepository.findAll()).thenReturn(Arrays.asList(posts));
        // when
        List<PostDto> returnedPosts = postsService.findPosts();
        // then
        assertTrue(returnedPosts.size() == 1);
        assertTrue(returnedPosts.get(0).getTitle() == posts.getTitle());
        assertTrue(returnedPosts.get(0).getAuthor() == posts.getAuthor());
        assertTrue(returnedPosts.get(0).getPost() == posts.getPost());
        assertTrue(returnedPosts.get(0).getStatus() == posts.getStatus());
        assertTrue(returnedPosts.get(0).getType() == posts.getType());
    }
}