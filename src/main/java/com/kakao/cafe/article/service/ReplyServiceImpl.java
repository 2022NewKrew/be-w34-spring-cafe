package com.kakao.cafe.article.service;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import com.kakao.cafe.article.dto.request.ArticleReplyCreateRequestDTO;
import com.kakao.cafe.article.dto.response.ArticleReplyFindResponseDTO;
import com.kakao.cafe.article.repository.ArticleReplyRepository;

@Service
@RequiredArgsConstructor
public class ArticleReplyServiceImpl implements ArticleReplyService {
    private final ArticleReplyRepository articleReplyRepository;

    @Override
    public void create(ArticleReplyCreateRequestDTO articleReplyCreateRequestDTO) {
        articleReplyRepository.save(articleReplyCreateRequestDTO.toEntity());
    }

    @Override
    public List<ArticleReplyFindResponseDTO> getAllArticleReplyByArticleId(int articleId) {
        return articleReplyRepository.findAllByArticleId(articleId).stream()
                                     .map(ArticleReplyFindResponseDTO::new)
                                     .collect(Collectors.toList());
    }

    @Override
    public void remove(int id) {
        articleReplyRepository.delete(id);
    }
}
