package com.dongeunpaeng.simplerestapp.web.dto;

import com.dongeunpaeng.simplerestapp.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostDto {
    private Long id;
    private Long author;
    private String title;
    private String post;
    private Long status;
    private Long type;
    private Boolean deleted;

    @Builder
    public PostDto(Posts entity) {
        this.id = entity.getId();
        this.author = entity.getAuthor();
        this.title = entity.getTitle();
        this.post = entity.getPost();
        this.status = entity.getStatus();
        this.type = entity.getType();
        this.deleted = entity.getDeleted();
    }

    public Posts toEntity() {
        return Posts.builder()
                .id(id)
                .author(author)
                .title(title)
                .post(post)
                .status(status)
                .type(type)
                .deleted(deleted)
                .build();
    }
}
