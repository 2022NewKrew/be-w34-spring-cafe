package com.kakao.cafe.article.service;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import com.kakao.cafe.article.dto.request.ArticleCreateRequestDTO;
import com.kakao.cafe.article.dto.request.ArticleUpdateRequestDTO;
import com.kakao.cafe.article.dto.response.ArticleFindResponseDTO;
import com.kakao.cafe.article.entity.Article;
import com.kakao.cafe.article.repository.ArticleRepository;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final ReplyService replyService;

    @Override
    public void create(ArticleCreateRequestDTO articleCreateRequestDTO) {
        articleRepository.save(articleCreateRequestDTO.toEntity());
    }

    @Override
    public List<ArticleFindResponseDTO> getAllArticle() {
        return articleRepository.findAll().stream()
                                .map(ArticleFindResponseDTO::new)
                                .collect(Collectors.toList());
    }

    @Override
    public ArticleFindResponseDTO getArticleById(int id) {
        Article article = articleRepository.findById(id).orElseThrow();

        ArticleFindResponseDTO articleFindResponseDTO = new ArticleFindResponseDTO(article);
        articleFindResponseDTO.setReplyList(replyService.getAllReplyByArticleId(id));

        return articleFindResponseDTO;
    }

    @Override
    public void update(int id, ArticleUpdateRequestDTO articleUpdateRequestDTO) {
        Article article = articleRepository.findById(id).orElseThrow();

        article.update(articleUpdateRequestDTO.getTitle(), articleUpdateRequestDTO.getContents());

        articleRepository.update(id, article);
    }

    @Override
    public void remove(int id) {
        articleRepository.delete(id);
    }
}
