package com.dongeunpaeng.simplerestapp.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostIdDto {
    private Long id;

    public PostIdDto(Long id) {
        this.id = id;
    }

}
