package com.example.kakaocafe.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostOfTableRow {
    private final long id;
    private final String writer;
    private final String title;
    private final String created;

    private final long viewCount;
}
