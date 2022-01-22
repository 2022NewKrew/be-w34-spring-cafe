package com.kakao.cafe.service;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.ArticleResponseDto;
import com.kakao.cafe.domain.article.ArticleSaveDto;
import com.kakao.cafe.domain.reply.Reply;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.exception.ForbiddenException;
import com.kakao.cafe.exception.NotFoundException;
import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.repository.ReplyRepository;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;

    @Autowired
    public ArticleService(
            ArticleRepository articleRepository,
            ReplyRepository replyRepository,
            UserRepository userRepository
    ) {
        this.articleRepository = articleRepository;
        this.replyRepository = replyRepository;
        this.userRepository = userRepository;
    }

    public void save(ArticleSaveDto dto) {
        Article article = new Article();
        article.setUserId(dto.getUserId());
        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());
        articleRepository.save(article);
    }

    public List<ArticleResponseDto> findAll() {
        return articleRepository.findAll().stream()
                .map(article -> {
                    User user = userRepository.findById(article.getUserId())
                            .orElse(null);
                    return new ArticleResponseDto(article, user);
                })
                .collect(Collectors.toList());
    }

    private Article getArticleById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 아이디의 게시물이 없습니다."));
    }

    public ArticleResponseDto findById(Long id) {
        Article article = getArticleById(id);
        User user = userRepository.findById(article.getUserId())
                .orElse(null);
        return new ArticleResponseDto(article, user);
    }

    public void update(Long id, ArticleSaveDto dto) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 아이디의 게시물이 없습니다."));
        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());
        articleRepository.save(article);
    }

    public void validateUserAndDeleteById(Long id, Long sessionUserId) {
        Article article = getArticleById(id);
        validateUserEqualsToSessionUser(article.getUserId(), sessionUserId);
        List<Reply> replies = replyRepository.findAllByArticle(id);
        validateUserEqualsToAllReplyUser(replies, article.getUserId());
        for (Reply reply : replies) {
            replyRepository.deleteById(reply.getId());
        }
        articleRepository.deleteById(id);
    }

    private void validateUserEqualsToSessionUser(Long articleUserId, Long sessionUserId) {
        if (!articleUserId.equals(sessionUserId)) {
            throw new ForbiddenException("게시물 유저와 로그인한 유저가 다릅니다.");
        }
    }

    private void validateUserEqualsToAllReplyUser(List<Reply> replies, Long userId) {
        if (!replies.stream().allMatch(reply -> reply.getUserId().equals(userId))) {
            throw new ForbiddenException("게시물 유저와 다른 댓글 유저가 존재합니다.");
        }
    }

    public ArticleResponseDto validateUserAndGetById(Long id, Long sessionUserId) {
        Article article = getArticleById(id);
        validateUserEqualsToSessionUser(article.getUserId(), sessionUserId);
        User user = userRepository.findById(article.getUserId())
                .orElse(null);
        return new ArticleResponseDto(article, user);
    }
}
