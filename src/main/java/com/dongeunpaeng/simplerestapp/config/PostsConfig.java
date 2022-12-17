package com.dongeunpaeng.simplerestapp.config;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dongeunpaeng.simplerestapp.domain.posts.Posts;
import com.dongeunpaeng.simplerestapp.domain.posts.PostsRepository;

@Configuration
class PostsConfig {

    @Bean
    CommandLineRunner commandLineRunner(PostsRepository postsRepository) {
        return args -> {
            Posts post1 = Posts.builder()
                    .author(1L)
                    .title("test title 1")
                    .post("test post 1")
                    .status(0L)
                    .type(0L)
                    .deleted(Boolean.FALSE)
                    .build();

            Posts post2 = Posts.builder()
                    .author(1L)
                    .title("test title 1")
                    .post("test post 1")
                    .status(0L)
                    .type(0L)
                    .deleted(Boolean.FALSE)
                    .build();

            postsRepository.saveAll(Arrays.asList(post1, post2));
        };
    }

}
