package com.kakao.cafe.article.service;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.dto.ArticleRequestDTO;
import com.kakao.cafe.article.dto.ArticleResponseDTO;
import com.kakao.cafe.article.repository.ArticleRepository;
import com.kakao.cafe.member.domain.Member;
import com.kakao.cafe.member.dto.MemberResponseDTO;
import com.kakao.cafe.member.dto.MemberUpdateRequestDTO;
import com.kakao.cafe.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final MemberService memberService;

    public Long posting(ArticleRequestDTO articleRequestDTO, Long sessionId) {
        Article article = articleRequestDTO.toArticle(LocalDate.now(), sessionId);
        return articleRepository.save(article).getId();
    }

    public ArticleResponseDTO findOne(Long id) {
        Article article = articleRepository.findOne(id).orElseThrow(() -> {
            throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다.");
        });

        return new ArticleResponseDTO(article, memberService.findOne(article.getMemberId()).getNickname());
    }

    public List<ArticleResponseDTO> findAll() {
        return articleRepository.findAll().stream()
                .map(article -> new ArticleResponseDTO(article, memberService.findOne(article.getMemberId()).getNickname()))
                .collect(Collectors.toList());
    }

    public void update(Long pathId, Long sessionId, ArticleRequestDTO articleRequestDTO) {
        Article article = articleRepository.findOne(pathId).orElseThrow(() -> {
            throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다.");
        });

        validateArticleAuthor(article.getMemberId(), sessionId);
        articleRepository.update(article.getId(), articleRequestDTO.toArticle(LocalDate.now(), sessionId));
    }

    public void delete(Long pathId, Long sessionId) {
        Article article = articleRepository.findOne(pathId).orElseThrow(() -> {
            throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다.");
        });

        validateArticleAuthor(article.getMemberId(), sessionId);
        articleRepository.delete(pathId);
    }

    private void validateArticleAuthor(Long pathId, Long sessionId) {
        if (!pathId.equals(sessionId)) {
            throw new IllegalArgumentException("다른 사람의 게시글을 수정/삭제할 수 없습니다.");
        }
    }
}
