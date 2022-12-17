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
    // FIXME: what about createdAt and modifiedAt?

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
                .deleted(deleted == null ? Boolean.FALSE : deleted)
                // FIXME: toEntity에서 null을 보내줬을 때 왜 Entity에서 자체적으로 default 값을 못 줄까
                .build();
    }
}
