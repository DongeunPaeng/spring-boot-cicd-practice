package com.dongeunpaeng.simplerestapp.service.posts;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
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
    public void getPost() throws Exception {
        // given - save virtual posts
        Posts posts = Posts.builder().id(1L).author(1L).title("test title").post("test post").status(0L).type(0L)
                .build();
        when(postsRepository.findById(any())).thenReturn(Optional.of(posts));
        // when
        PostDto returnedPosts = postsService.getPost(123L);
        // then
        Assertions.assertThat(returnedPosts).isNotNull();
        assertEquals(returnedPosts.getId(), posts.getId());
        assertEquals(returnedPosts.getTitle(), posts.getTitle());
        assertEquals(returnedPosts.getAuthor(), posts.getAuthor());
        assertEquals(returnedPosts.getPost(), posts.getPost());
        assertEquals(returnedPosts.getStatus(), posts.getStatus());
        assertEquals(returnedPosts.getType(), posts.getType());
    }

    @Test
    public void getPosts() {
        // given - save virtual posts
        Posts posts = Posts.builder().id(1L).author(1L).title("test title").post("test post").status(0L).type(0L)
                .build();
        when(postsRepository.findAll()).thenReturn(Arrays.asList(posts));
        // when
        List<PostDto> returnedPosts = postsService.getPosts();
        // then
        assertTrue(returnedPosts.size() == 1);
        assertTrue(returnedPosts.get(0).getId() == posts.getId());
        assertTrue(returnedPosts.get(0).getTitle() == posts.getTitle());
        assertTrue(returnedPosts.get(0).getAuthor() == posts.getAuthor());
        assertTrue(returnedPosts.get(0).getPost() == posts.getPost());
        assertTrue(returnedPosts.get(0).getStatus() == posts.getStatus());
        assertTrue(returnedPosts.get(0).getType() == posts.getType());
    }

    @Test
    public void getDrafts() {
        // given - save virtual posts
        Posts post1 = Posts.builder().id(1L).author(1L).title("test title").post("test post").status(0L).type(1L)
                .build();
        Posts post2 = Posts.builder().id(2L).author(1L).title("test title").post("test post").status(1L).type(1L)
                .build();
        Posts post3 = Posts.builder().id(3L).author(1L).title("test title").post("test post").status(2L).type(0L)
                .build();
        Posts post4 = Posts.builder().id(4L).author(1L).title("test title").post("test post").status(2L).type(1L)
                .build();
        when(postsRepository.findAllByStatusGreaterThan(0L)).thenReturn(Arrays.asList(post2, post3, post4));
        // when
        List<PostDto> returnedPosts = postsService.getDrafts();
        // then
        assertTrue(returnedPosts.size() == 3);
        assertTrue(!returnedPosts.contains(PostDto.builder().entity(post1).build()));
    }

    @Test
    public void savePost() {
        // given - make virtual dto without ID
        Posts posts = Posts.builder().author(1L).title("test title").post("test post").status(0L).type(0L).build();
        PostDto saveDto = PostDto.builder().entity(posts).build();
        when(postsRepository.save(any())).thenReturn(posts);
        ReflectionTestUtils.setField(posts, "id", 123L);
        // when
        Long returnedId = postsService.savePost(saveDto);
        // then
        assertEquals(returnedId, 123L);
    }

    @Test
    public void editPost() {
        // given
        Posts posts = Posts.builder().id(123L).author(1L).title("test title").post("test post").status(0L).type(0L)
                .build();
        PostDto editDto = PostDto.builder().entity(posts).build();
        when(postsRepository.findById(123L)).thenReturn(Optional.of(posts));
        when(postsRepository.save(any())).thenReturn(posts);
        ReflectionTestUtils.setField(posts, "id", 123L);
        // when
        Long returnedId = postsService.editPost(editDto);
        // then
        assertEquals(returnedId, 123L);
    }

    @Test
    public void deletePost() {
        // given
        Posts posts = Posts.builder().id(123L).author(1L).title("test title").post("test post").status(0L).type(0L)
                .build();
        PostDto deleteDto = PostDto.builder().entity(posts).build();
        when(postsRepository.findById(123L)).thenReturn(Optional.of(posts));
        // when
        postsService.deletePost(deleteDto);
        // then... no then
    }
}
