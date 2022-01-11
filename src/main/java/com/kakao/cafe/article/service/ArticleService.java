package com.kakao.cafe.article.service;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.dto.ArticleRequestDTO;
import com.kakao.cafe.article.dto.ArticleResponseDTO;
import com.kakao.cafe.article.repository.ArticleRepository;
import com.kakao.cafe.member.domain.Member;
import com.kakao.cafe.member.dto.MemberRequestDTO;
import com.kakao.cafe.member.dto.MemberResponseDTO;
import com.kakao.cafe.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Long posting(ArticleRequestDTO articleRequestDTO) {
        Article article = articleRequestDTO.toArticle(LocalDate.now());
        return articleRepository.save(article);
    }

    public ArticleResponseDTO findOne(Long id) {
        return new ArticleResponseDTO(articleRepository.findOne(id));
    }

    public List<ArticleResponseDTO> findAll() {
        return articleRepository.findAll().stream()
                .map(ArticleResponseDTO::new)
                .collect(Collectors.toList());
    }

    public void update(Long id) {
        // articleRepository.update(id);
    }
}
