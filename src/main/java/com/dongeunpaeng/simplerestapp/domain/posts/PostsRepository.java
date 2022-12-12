package com.dongeunpaeng.simplerestapp.domain.posts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    List<Posts> findAllByStatus(Long type);

    List<Posts> findAllByStatusGreaterThan(Long type);
}
