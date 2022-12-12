package com.dongeunpaeng.simplerestapp.domain.posts;

import com.dongeunpaeng.simplerestapp.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "posts")
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
    @ColumnDefault("0")
    private Long status;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Long type;
}