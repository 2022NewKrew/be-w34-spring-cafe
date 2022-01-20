package com.kakao.cafe.service;

import com.kakao.cafe.dto.ReplyDto;
import com.kakao.cafe.entity.Article;
import com.kakao.cafe.entity.Reply;
import com.kakao.cafe.entity.User;

import java.util.List;

public interface ReplyService {
    Long register(ReplyDto replyDto);

    List<ReplyDto> getList(Long articleId);

    void modify(ReplyDto replyDto);

    void remove(Long replyId);

    default Reply dtoToEntity(ReplyDto replyDto) {
        Article article = Article.builder().articleId(replyDto.getArticleId()).build();
        User user = User.builder().email(replyDto.getWriterEmail()).username(replyDto.getWriterUsername()).build();
        return Reply.builder()
                .article(article)
                .writer(user)
                .replyId(replyDto.getReplyId())
                .content(replyDto.getContent())
                .build();
    }

    default ReplyDto entityToDto(Reply reply) {
        return ReplyDto.builder()
                .articleId(reply.getArticle().getArticleId())
                .writerEmail(reply.getWriter().getEmail())
                .writerUsername(reply.getWriter().getUsername())
                .replyId(reply.getReplyId())
                .content(reply.getContent())
                .regDate(reply.getRegDate())
                .modDate(reply.getModDate())
                .build();
    }
}
