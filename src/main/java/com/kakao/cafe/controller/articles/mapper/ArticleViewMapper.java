package com.kakao.cafe.controller.articles.mapper;

import com.kakao.cafe.controller.articles.dto.response.*;
import com.kakao.cafe.service.article.dto.ArticleInfo;
import com.kakao.cafe.service.article.dto.ReplyInfo;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArticleViewMapper {

    public List<ArticleItemResponse> toArticleItemResponseList(List<ArticleInfo> articleInfoList) {
        return articleInfoList.stream()
                .map(articleInfo -> toArticleItemResponse(articleInfo))
                .collect(Collectors.toList());
    }

    public ArticleItemResponse toArticleItemResponse(ArticleInfo articleInfo) {
        return ArticleItemResponse.builder()
                .id(articleInfo.getId())
                .title(articleInfo.getTitle())
                .writer(articleInfo.getWriterName())
                .build();
    }

    public ArticleDetailResponse toArticleDetailResponse(ArticleInfo articleInfo) {
        return ArticleDetailResponse.builder()
                .articleId(articleInfo.getId())
                .writerId(articleInfo.getWriterId())
                .writerName(articleInfo.getWriterName())
                .title(articleInfo.getTitle())
                .contents(articleInfo.getContents())
                .canUpdate(articleInfo.getCanUpdate())
                .build();
    }

    public ReplyListResponse toReplyListResponse(List<ReplyInfo> replies) {
        return ReplyListResponse.builder()
                .length(replies.size())
                .replies(replies.stream().map(this::toReplyResponse).collect(Collectors.toList()))
                .build();
    }

    public ReplyResponse toReplyResponse(ReplyInfo replyInfo) {
        return ReplyResponse.builder()
                .replyId(replyInfo.getReplyId())
                .articleId(replyInfo.getArticleId())
                .writerId(replyInfo.getWriterId())
                .writerName(replyInfo.getWriterName())
                .comment(replyInfo.getComment())
                .createdTime(replyInfo.getCreatedTime())
                .canUpdate(replyInfo.getCanUpdate())
                .build();
    }

    public ArticleUpdateFormResponse toArticleUpdateFormResponse(ArticleInfo articleInfo) {
        return ArticleUpdateFormResponse.builder()
                .articleId(articleInfo.getId())
                .writerId(articleInfo.getWriterId())
                .writerName(articleInfo.getWriterName())
                .title(articleInfo.getTitle())
                .contents(articleInfo.getContents())
                .build();
    }
}
