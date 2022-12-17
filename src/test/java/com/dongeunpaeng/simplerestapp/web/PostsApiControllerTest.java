package com.dongeunpaeng.simplerestapp.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.Arrays;

import com.dongeunpaeng.simplerestapp.domain.posts.Posts;
import com.dongeunpaeng.simplerestapp.service.posts.PostsService;
import com.dongeunpaeng.simplerestapp.web.dto.PostDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(PostsApiController.class)
class PostsApiControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private PostsService postsService;

    Posts post1 = Posts.builder().author(1L).title("test title1").post("test post1").status(0L).type(1L).build();
    Posts post2 = Posts.builder().author(1L).title("test title2").post("test post2").status(1L).type(1L).build();
    Posts post3 = Posts.builder().author(1L).title("test title3").post("test post3").status(2L).type(0L).build();
    Posts post4 = Posts.builder().author(1L).title("test title4").post("test post4").status(2L).type(1L).build();
    PostDto postDto1 = PostDto.builder().entity(post1).build();
    PostDto postDto2 = PostDto.builder().entity(post2).build();
    PostDto postDto3 = PostDto.builder().entity(post3).build();
    PostDto postDto4 = PostDto.builder().entity(post4).build();

    Posts postGiven = Posts.builder().id(123L).author(1L).title("test title4").post("test post4").status(2L).type(1L)
            .build();
    PostDto postGivenDto = PostDto.builder().entity(postGiven).build();

    @BeforeEach
    void initEach() {
        when(postsService.getPosts()).thenReturn(Arrays.asList(postDto1, postDto2, postDto3, postDto4));
        when(postsService.getPost(1L)).thenReturn(postDto1);
        when(postsService.getDrafts()).thenReturn(Arrays.asList(postDto2, postDto3, postDto4));
        when(postsService.savePost(any())).thenReturn(123L);
        when(postsService.editPost(any())).thenReturn(123L);
    }

    @Test
    public void getPosts() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/v1/post")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].status").value(0L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].status").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].status").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[3].status").value(2L));
    }

    @Test
    public void getPost() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/v1/post/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(0L));
    }

    @Test
    public void getDrafts() throws Exception {
        // TODO: need token
        mvc.perform(MockMvcRequestBuilders
                .get("/api/v1/post/draft")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].status").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].status").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].status").value(2L));
    }

    @Test
    public void savePost() throws Exception {
        // TODO: need token
        String reqBody = new ObjectMapper().writeValueAsString(postDto1);
        mvc.perform(MockMvcRequestBuilders
                .post("/api/v1/post/write")
                .content(reqBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(123L));
    }

    @Test
    public void editPost() throws Exception {
        // TODO: need token
        String reqBody = new ObjectMapper().writeValueAsString(postGivenDto);
        mvc.perform(MockMvcRequestBuilders
                .put("/api/v1/post/edit")
                .content(reqBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(123L));
    }

    @Test
    public void deletePost() throws Exception {
        // TODO: need token
        String reqBody = "1";
        mvc.perform(MockMvcRequestBuilders
                .delete("/api/v1/post/delete")
                .content(reqBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("successfully deleted"));
    }
}
