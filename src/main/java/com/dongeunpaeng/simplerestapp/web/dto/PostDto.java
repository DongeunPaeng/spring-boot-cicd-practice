package com.dongeunpaeng.simplerestapp.web.dto;

import com.dongeunpaeng.simplerestapp.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostDto {
    private Long author;
    private String title;
    private String post;
    private Long status;
    private Long type;

    @Builder
    public PostDto(Posts entity) {
        this.author = entity.getAuthor();
        this.title = entity.getTitle();
        this.post = entity.getPost();
        this.status = entity.getStatus();
        this.type = entity.getType();
    }

    public Posts toEntity() {
        return Posts.builder()
                .author(author)
                .title(title)
                .post(post)
                .status(status)
                .type(type)
                .build();
    }
}
