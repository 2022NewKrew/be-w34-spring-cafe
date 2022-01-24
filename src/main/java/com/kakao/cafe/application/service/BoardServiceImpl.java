package com.kakao.cafe.application.service;

import com.kakao.cafe.application.dto.ArticleDto;
import com.kakao.cafe.application.dto.CommentDto;
import com.kakao.cafe.application.dto.PaginationDto;
import com.kakao.cafe.model.domain.Article;
import com.kakao.cafe.model.domain.Comment;
import com.kakao.cafe.model.repository.ArticleRepository;
import com.kakao.cafe.model.repository.CommentRepository;
import com.kakao.cafe.util.exception.ArticleNotFoundException;
import com.kakao.cafe.util.exception.CommentNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.kakao.cafe.util.Constants.DEFAULT_NAVIGATION_SIZE;

@Service
public class BoardServiceImpl implements BoardService {
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BoardServiceImpl(@Qualifier("ArticleRepositoryJdbcImpl") ArticleRepository articleRepository,
                            @Qualifier("CommentRepositoryJdbcImpl") CommentRepository commentRepository,
                            ModelMapper modelMapper) {
        this.articleRepository = articleRepository;
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void writeArticle(ArticleDto articleDto) {
        articleRepository.saveArticle(modelMapper.map(articleDto, Article.class));
    }

    @Override
    public CommentDto writeComment(long articleId, CommentDto commentDto) {
        return commentRepository.saveComment(articleId, modelMapper.map(commentDto, Comment.class)).stream()
                .map(comment -> modelMapper.map(comment, CommentDto.class))
                .findFirst()
                .orElseThrow(ArticleNotFoundException::new);
    }

    @Override
    public List<ArticleDto> findAllArticle() {
        return articleRepository.findAllArticle().stream()
                .map(article -> modelMapper.map(article, ArticleDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ArticleDto findArticleByArticleId(long articleId) {
        return articleRepository.findArticleByArticleId(articleId).stream()
                .map(article -> modelMapper.map(article, ArticleDto.class))
                .findFirst()
                .orElseThrow(ArticleNotFoundException::new);
    }

    @Override
    public List<ArticleDto> findArticlesByWriterId(String writerId) {
        return articleRepository.findArticlesByWriterId(writerId).stream()
                .map(article -> modelMapper.map(article, ArticleDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleDto> findArticlesByCurrentPageAndCountPerPage(long currentPage, int countPerPage) {
        long start = (currentPage - 1) * countPerPage;

        return articleRepository.findArticlesByStartAndWantedCountPerPage(start, countPerPage).stream()
                .map(article -> modelMapper.map(article, ArticleDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> findCommentsByArticleId(long articleId) {
        List<Comment> commentList = commentRepository.findCommentsByArticleId(articleId);

        if (commentList.isEmpty()) {
            return new ArrayList<>();
        }

        return commentList.stream()
                .map(comment -> modelMapper.map(comment, CommentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CommentDto findCommentByArticleIdAndCommentId(long articleId, long commentId) {
        return commentRepository.findCommentByArticleIdAndCommentId(articleId, commentId).stream()
                .map(comment -> modelMapper.map(comment, CommentDto.class))
                .findFirst()
                .orElseThrow(CommentNotFoundException::new);
    }

    @Override
    public List<CommentDto> findCommentByWriterId(String writerId) {
        return commentRepository.findCommentsByWriterId(writerId).stream()
                .map(comment -> modelMapper.map(comment, CommentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void modifyArticle(ArticleDto articleDto) {
        if (!articleRepository.modifyArticle(modelMapper.map(articleDto, Article.class))) {
            throw new ArticleNotFoundException();
        }
    }

    @Override
    public void modifyComment(long articleId, CommentDto commentDto) {
        if (!commentRepository.modifyComment(articleId, modelMapper.map(commentDto, Comment.class))) {
            throw new CommentNotFoundException();
        }
    }

    @Override
    public void deleteArticle(long articleId) {
        if (!articleRepository.deleteArticle(articleId)) {
            throw new ArticleNotFoundException();
        }
    }

    @Override
    public void deleteCommentByArticleId(long articleId) {
        if (!commentRepository.deleteCommentByArticleId(articleId)) {
            throw new CommentNotFoundException("해당 게시글의 댓글이 존재하지 않습니다.");
        }
    }

    @Override
    public void deleteComment(long articleId, long commentId) {
        if (!commentRepository.deleteComment(articleId, commentId)) {
            throw new CommentNotFoundException();
        }
    }

    @Override
    public boolean isSameArticleWriter(long articleId, String writerId) {
        return articleRepository.findArticleByArticleId(articleId)
                .stream().anyMatch(a -> a.getWriterId().equals(writerId));
    }

    @Override
    public boolean isSameCommentWriter(long articleId, long commentId, String writerId) {
        return commentRepository.findCommentByArticleIdAndCommentId(articleId, commentId)
                .stream().anyMatch(a -> a.getWriterId().equals(writerId));
    }

    @Override
    public PaginationDto makePaginationInfo(long currentPage, long countPerPage) {
        long totalCount = articleRepository.countAllAvailableArticles();
        long totalPageCount = ((totalCount - 1) / countPerPage) + 1;
        boolean startRange = currentPage <= DEFAULT_NAVIGATION_SIZE;
        boolean endRange = ((totalPageCount - 1) / DEFAULT_NAVIGATION_SIZE) * DEFAULT_NAVIGATION_SIZE < currentPage;
        long startPage = ((currentPage - 1) / DEFAULT_NAVIGATION_SIZE) * DEFAULT_NAVIGATION_SIZE + 1;
        long endPage = Math.min(startPage + DEFAULT_NAVIGATION_SIZE - 1, totalPageCount);

        return PaginationDto.builder()
                .totalCount(totalCount)
                .totalPageCount(totalPageCount)
                .startRange(startRange)
                .endRange(endRange)
                .startPage(startPage)
                .endPage(endPage)
                .currentPage(currentPage)
                .build();
    }
}
