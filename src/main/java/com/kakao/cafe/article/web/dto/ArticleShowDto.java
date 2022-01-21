package com.kakao.cafe.article.web.dto;

import com.kakao.cafe.reply.web.dto.ReplyShowDto;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ArticleShowDto {

    private Long index;
    private String writer;
    private String title;
    private String contents;
    private List<ReplyShowDto> replies;
}
