package com.kakao.cafe.model.service;

import com.kakao.cafe.model.domain.Article;
import com.kakao.cafe.model.domain.Comment;
import com.kakao.cafe.model.dto.ArticleDto;
import com.kakao.cafe.model.dto.CommentDto;
import com.kakao.cafe.model.repository.BoardRepository;
import com.kakao.cafe.util.exception.ArticleNotFoundException;
import com.kakao.cafe.util.exception.CommentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper;

    @Override
    public void writeArticle(ArticleDto articleDto) {
        boardRepository.saveArticle(modelMapper.map(articleDto, Article.class));
    }

    @Override
    public void writeComment(long articleId, CommentDto commentDto) {
        if (!boardRepository.saveComment(articleId, modelMapper.map(commentDto, Comment.class))) {
            throw new ArticleNotFoundException("해당 게시글이 존재하지 않습니다.");
        }
    }

    @Override
    public List<ArticleDto> findAllArticle() {
        return boardRepository.findAllArticle().stream()
                .map(article -> modelMapper.map(article, ArticleDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ArticleDto findArticleByArticleId(long articleId) {
        return boardRepository.findArticleByArticleId(articleId).stream()
                .map(article -> modelMapper.map(article, ArticleDto.class))
                .findFirst()
                .orElseThrow(() -> new ArticleNotFoundException("해당 게시글이 존재하지 않습니다."));
    }

    @Override
    public List<ArticleDto> findArticlesByWriterId(String writer) {
        return boardRepository.findArticlesByWriterId(writer).stream()
                .map(article -> modelMapper.map(article, ArticleDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> findCommentsByArticleId(long articleId) {
        List<Comment> commentList = boardRepository.findCommentsByArticleId(articleId);

        if (commentList.isEmpty()) {
            return new ArrayList<>();
        }

        return commentList.stream()
                .map(comment -> modelMapper.map(comment, CommentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CommentDto findComment(long articleId, long commentId) {
        return boardRepository.findComment(articleId, commentId).stream()
                .map(comment -> modelMapper.map(comment, CommentDto.class))
                .findFirst()
                .orElseThrow(() -> new CommentNotFoundException("해당 댓글이 존재하지 않습니다."));
    }

    @Override
    public void modifyArticle(ArticleDto articleDto) {
        if (!boardRepository.modifyArticle(modelMapper.map(articleDto, Article.class))) {
            throw new ArticleNotFoundException("해당 게시글이 존재하지 않습니다.");
        }
    }

    @Override
    public void modifyComment(long articleId, CommentDto commentDto) {
        if (!boardRepository.modifyComment(articleId, modelMapper.map(commentDto, Comment.class))) {
            throw new CommentNotFoundException("해당 뎃글이 존재하지 않습니다.");
        }
    }

    @Override
    public void deleteArticle(long articleId) {
        if (!boardRepository.deleteArticle(articleId)) {
            throw new ArticleNotFoundException("해당 게시글이 존재하지 않습니다.");
        }
    }

    @Override
    public void deleteComment(long articleId, long commentId) {
        if (!boardRepository.deleteComment(articleId, commentId)) {
            throw new CommentNotFoundException("해당 뎃글이 존재하지 않습니다.");
        }
    }
}
