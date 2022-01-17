package com.kakao.cafe.config;

import com.kakao.cafe.domain.article.*;
import com.kakao.cafe.domain.member.*;
import com.kakao.cafe.dto.*;

import java.util.stream.Collectors;

public class Mapper {

    public Member map(JoinMemberDto memberDto) {
        Long memberId = memberDto.getMemberId();
        UserId userId = new UserId(memberDto.getUserId());
        Name name = new Name(memberDto.getName());
        Password password = new Password(memberDto.getPassword());
        Email email = new Email(memberDto.getEmail());
        Member member = new Member(userId, name, password, email);
        member.setMemberId(memberId);
        return member;
    }

    public InquireMemberDto map(Member member) {
        return new InquireMemberDto(member.getMemberId(),
                member.getUserId().getUserId(),
                member.getName().getName(),
                member.getEmail().getEmail());
    }

    public Article map(PostArticleDto articleDto) {
        return new Article(new Title(articleDto.getTitle()),
                new Text(articleDto.getContents()),
                Time.now());
    }

    public ArticleListDto convertToList(Article article) {
        return new ArticleListDto(article.getArticleId(),
                article.getAuthor().getMemberId(),
                article.getTitle().toString(),
                article.getAuthor().getUserId().getUserId(),
                article.getTime().toString());
    }

    public InquireArticleDto map(Article article) {
        return new InquireArticleDto(article.getArticleId(),
                article.getAuthor().getMemberId(),
                article.getTitle().getTitle(),
                article.getText().toString(),
                article.getAuthor().getUserId().getUserId(),
                article.getTime().toString(),
                article.getComments()
                        .getComments()
                        .stream()
                        .map(this::map)
                        .collect(Collectors.toList()));
    }

    public CommentDto map(Comment comment) {
        return new CommentDto(comment.getAuthor().getMemberId(),
                comment.getAuthor().getUserId().getUserId(),
                comment.getText().toString(),
                comment.getTime().toString());
    }
}
