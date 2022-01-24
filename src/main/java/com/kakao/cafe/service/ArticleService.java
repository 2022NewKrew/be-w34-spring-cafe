package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.dto.ArticleRequestDTO;
import com.kakao.cafe.dto.ArticleResponseDTO;
import com.kakao.cafe.dto.ReplyRequestDTO;
import com.kakao.cafe.dto.ReplyResponseDTO;
import com.kakao.cafe.error.exception.ArticleNotFoundException;
import com.kakao.cafe.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final ReplyService replyService;

    @Transactional
    public void create(ArticleRequestDTO articleRequestDto) {
        articleRepository.save(articleRequestDto);
    }

    @Transactional
    public void create(ReplyRequestDTO replyRequestDTO) {
        replyService.create(replyRequestDTO);
    }


    @Transactional(readOnly = true)
    public ArticleResponseDTO read(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(ArticleNotFoundException::new);
        List<Reply> replies = replyService.readAll(id);
        article.setReplies(replies);
        return mapper(article);
    }

    @Transactional(readOnly = true)
    public List<ArticleResponseDTO> readAll() {
        return articleRepository.findAll()
                .stream()
                .map(this::mapper)
                .collect(Collectors.toUnmodifiableList());
    }

    @Transactional(readOnly = true)
    public ReplyResponseDTO readReply(Long id) {
        return replyService.read(id);
    }

    @Transactional
    public void update(Long id, ArticleRequestDTO articleRequestDTO) {
        Article article = articleRepository.findById(id)
                .orElseThrow(ArticleNotFoundException::new);
        articleRepository.update(id, articleRequestDTO);
    }

    @Transactional
    public void delete(Long id) {
        articleRepository.findById(id).orElseThrow(ArticleNotFoundException::new);
        replyService.deleteAll(id);
        articleRepository.delete(id);
    }

    @Transactional
    public void deleteReply(Long id) {
        replyService.delete(id);
    }

    private ArticleResponseDTO mapper(Article article) {
        return ArticleResponseDTO.builder()
                .id(article.getId())
                .author(article.getAuthor())
                .authorName(article.getAuthorName())
                .title(article.getTitle())
                .content(article.getContent())
                .replies(Optional.ofNullable(article.getReplies())
                        .orElseGet(ArrayList::new)
                        .stream()
                        .map(this::mapper)
                        .collect(Collectors.toUnmodifiableList()))
                .createdAt(article.getCreatedAt())
                .updatedAt(article.getUpdatedAt())
                .build();
    }

    private ReplyResponseDTO mapper(Reply reply) {
        return ReplyResponseDTO.builder()
                .id(reply.getId())
                .author(reply.getAuthor())
                .authorName(reply.getAuthorName())
                .content(reply.getContent())
                .createdAt(reply.getCreatedAt())
                .build();
    }
}
