package com.kakao.cafe.qna.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class PackedArticleDTO {
    private final ArticleDTO article;
    private final List<ReplyDTO> replies;
    private final int numReply;
}
