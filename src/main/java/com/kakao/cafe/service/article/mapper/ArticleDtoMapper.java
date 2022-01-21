package com.kakao.cafe.service.article.mapper;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.service.article.dto.ArticleInfo;
import com.kakao.cafe.service.article.dto.ReplyInfo;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ArticleDtoMapper {

    public ArticleInfo toArticleInfo(Article article) {
        return toArticleInfo(article, "");
    }

    public ArticleInfo toArticleInfo(Article article, String loginId) {
        return ArticleInfo.builder()
                .id(article.getId())
                .writerId(article.getWriter().getUserId())
                .writerName(article.getWriter().getUserName())
                .title(article.getTitle())
                .contents(article.getContents())
                .canUpdate(article.isWriter(loginId))
                .build();
    }

    public ReplyInfo toReplyInfo(Reply reply, String loginId) {
        LocalDateTime updatedTime = reply.getUpdatedTime();
        return ReplyInfo.builder()
                .replyId(reply.getId())
                .articleId(reply.getArticleId())
                .writerId(reply.getWriter().getUserId())
                .writerName(reply.getWriter().getUserName())
                .comment(reply.getComment())
                .createdTime(reply.getCreatedTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd a hh:mm")))
                .updatedTime(updatedTime != null ? updatedTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd a hh:mm")) : "")
                .canUpdate(reply.isWriter(loginId))
                .build();
    }

}
