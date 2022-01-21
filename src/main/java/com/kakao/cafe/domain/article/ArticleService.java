package com.kakao.cafe.domain.article;

import com.kakao.cafe.domain.article.dto.*;
import com.kakao.cafe.domain.article.reply.Reply;
import com.kakao.cafe.domain.article.reply.ReplyService;
import com.kakao.cafe.util.Paging;
import com.kakao.cafe.util.PagingRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ReplyService replyService;

    public ArticleService(ArticleRepository articleRepository, ReplyService replyService) {
        this.articleRepository = articleRepository;
        this.replyService = replyService;
    }

    @Transactional
    public Long createArticle(ArticleRequestDto requestDto, String currentUserId) {
        Article article = requestDto.toArticle();
        article.checkAuthor(currentUserId);
        return articleRepository.save(requestDto.toArticle());
    }

    @Transactional
    public Long updateArticle(Long articleId, String currentUserId, ArticleRequestDto requestDto) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다."));
        article.update(requestDto.getTitle(), requestDto.getContent(), currentUserId);
        return articleRepository.save(article);
    }

    @Transactional
    public Long deleteArticle(Long articleId, String currentUserId) {
        Article article = findArticleWithReplies(articleId);
        article.delete(currentUserId);
        article.getReplies().forEach(reply -> replyService.deleteReply(article, reply, currentUserId));
        return articleRepository.save(article);
    }

    @Transactional
    public Long createReply(Long articleId, ReplyRequestDto replyRequestDto, String currentUserId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다."));
        Reply reply = replyRequestDto.toReply(article);
        return replyService.createReply(reply, currentUserId);
    }

    @Transactional
    public Long deleteReply(Long articleId, Long replyId, String currentUserId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다."));
        Reply reply = replyService.findReplyByArticleAndId(article, replyId);
        return replyService.deleteReply(article, reply, currentUserId);
    }

    @Transactional(readOnly = true)
    public ArticleRepliesResponseDto retrieveArticleWithReplies(Long articleId) {
        Article article = findArticleWithReplies(articleId);
        return new ArticleRepliesResponseDto(article);
    }

    @Transactional(readOnly = true)
    public ArticleSimpleResponseDto retrieveArticleForUpdate(Long articleId, String currentUserId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다."));
        article.checkAuthor(currentUserId);
        return new ArticleSimpleResponseDto(article);
    }

    @Transactional(readOnly = true)
    public List<ArticleSimpleResponseDto> retrieveAllArticles() {
        return articleRepository.findAll().stream().map(ArticleSimpleResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Paging<ArticleSimpleResponseDto> retrievePagingOfArticles(PagingRequest pagingRequest) {
        Paging<Article> articles = articleRepository.findByPageRequest(pagingRequest);
        List<ArticleSimpleResponseDto> responses = articles.getPagingList().stream().map(ArticleSimpleResponseDto::new).collect(Collectors.toList());
        return new Paging<>(responses, articles.getLimit(), articles.getOffset(), articles.getTotal());
    }

    @Transactional(readOnly = true)
    public ReplyResponseDto retrieveReply(Long articleId, Long replyId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다."));
        Reply reply = replyService.findReplyByArticleAndId(article, replyId);
        return new ReplyResponseDto(reply);
    }

    private Article findArticleWithReplies(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다."));
        List<Reply> replies = replyService.findRepliesByArticle(article);
        article.setReplies(replies);
        return article;
    }
}
