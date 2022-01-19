package com.kakao.cafe.qna.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class PackedArticleDTO {
    private final WrittenThingDTO article;
    private final List<WrittenThingDTO> replies;
}
