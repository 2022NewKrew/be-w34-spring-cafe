package com.kakao.cafe.service;

import com.kakao.cafe.model.dto.CommentDto;
import com.kakao.cafe.model.vo.CommentVo;
import com.kakao.cafe.repository.ArticleDao;
import com.kakao.cafe.model.dto.ArticleDto;
import com.kakao.cafe.model.dto.UserDto;
import com.kakao.cafe.model.vo.ArticleVo;
import com.kakao.cafe.model.vo.UserVo;
import com.kakao.cafe.service.validation.ArticleValidation;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleDao articleDao;
    private final ArticleValidation articleValidation;
    private final ModelMapper modelMapper;

    Logger logger = LoggerFactory.getLogger(ArticleService.class);

    public ArticleService(ArticleDao articleDao, ArticleValidation articleValidation, ModelMapper modelMapper) {
        this.articleDao = articleDao;
        this.articleValidation = articleValidation;
        this.modelMapper = modelMapper;
    }

    public List<ArticleDto> getArticleList() {
        return articleDao.findAllArticle().stream()
                .map(articleVo -> modelMapper.map(articleVo, ArticleDto.class))
                .collect(Collectors.toList());
    }

    public List<CommentDto> getCommentList(int index) {
        return articleDao.findAllComments(index).stream()
                .map(commentVo -> modelMapper.map(commentVo, CommentDto.class))
                .collect(Collectors.toList());
    }

    public ArticleDto filterArticleByIndex(int index) {
        ArticleVo articleVo = articleDao.filterArticleByIndex(index);
        articleValidation.validateArticle(articleVo);
        return modelMapper.map(articleVo, ArticleDto.class);
    }

    public CommentDto filterCommentById(int commentId) {
        CommentVo commentVo = articleDao.filterCommentById(commentId);
        return modelMapper.map(commentVo, CommentDto.class);
    }

    public void writeArticle(ArticleDto article, UserDto user) {
        article.setWriter(modelMapper.map(user, UserVo.class));
        articleDao.writeArticle(modelMapper.map(article, ArticleVo.class));
    }

    public void updateArticle(int index, ArticleDto article) {
        articleDao.updateArticle(index, modelMapper.map(article, ArticleVo.class));
    }

    public void deleteArticle(int index, UserDto user) {
        List<CommentVo> comments = articleDao.findAllComments(index);
        articleValidation.validateDeleteArticle(comments, user);
        articleDao.deleteArticle(index);
    }

    public void writerComment(int index, CommentDto comment, UserDto user) {
        comment.setWriter(modelMapper.map(user, UserVo.class));
        articleDao.writerComment(index, modelMapper.map(comment, CommentVo.class));
    }

    public void deleteComment(int commentId) {
        articleDao.deleteComment(commentId);
    }
}
