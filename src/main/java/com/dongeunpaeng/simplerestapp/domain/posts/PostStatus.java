package com.dongeunpaeng.simplerestapp.domain.posts;

public enum PostStatus {
    PUBLIC(0L),
    PRIVATE(1L),
    DELETED(2L);

    public final Long numeric;

    PostStatus(Long numeric) {
        this.numeric = numeric;
    }
}
