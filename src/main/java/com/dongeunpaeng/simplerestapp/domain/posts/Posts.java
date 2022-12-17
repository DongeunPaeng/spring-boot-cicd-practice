package com.dongeunpaeng.simplerestapp.domain.posts;

import com.dongeunpaeng.simplerestapp.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long author;

    @Column(columnDefinition = "VARCHAR(200)", nullable = false)
    private String title;

    @Column(columnDefinition = "MEDIUMTEXT", nullable = false)
    private String post;

    @Column(nullable = false)
    private Long status;

    @Column(nullable = false)
    private Long type;

    @Builder
    public Posts(Long id, Long author, String title, String post, Long status, Long type) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.post = post;
        this.status = status;
        this.type = type;
    }
}
