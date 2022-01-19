package com.kakao.cafe.service;

import com.kakao.cafe.dao.ArticleDao;
import com.kakao.cafe.dto.article.ArticleDto;
import com.kakao.cafe.vo.ArticleVo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final ArticleDao articleDao;
    private final ModelMapper modelMapper;

    public void addArticle(ArticleDto articleDto) {
        articleDto.setDate(LocalDateTime.now().format(formatter));
        ArticleVo articleVo = modelMapper.map(articleDto,ArticleVo.class);
        articleDao.save(articleVo);
    }

    public ArticleDto getArticle(int articleId) {
        ArticleVo articleVo = articleDao.findByArticleId(articleId);
        return modelMapper.map(articleVo,ArticleDto.class);
    }

    public List<ArticleDto> getArticles() {
        return articleDao.findAll().stream()
                .map(articleVo -> modelMapper.map(articleVo,ArticleDto.class))
                .collect(Collectors.toList());
    }

    public void updateArticle(ArticleDto articleDto) {
        articleDto.setDate(LocalDateTime.now().format(formatter));
        ArticleVo articleVo = modelMapper.map(articleDto,ArticleVo.class);
        articleDao.update(articleVo);
    }

    public void deleteArticle(int articleId) {
        articleDao.deleteByArticleId(articleId);
    }
}
