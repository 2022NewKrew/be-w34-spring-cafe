package com.kakao.cafe.service;

import com.kakao.cafe.dao.ArticleDao;
import com.kakao.cafe.dto.article.ArticleDto;
import com.kakao.cafe.vo.ArticleVo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final ArticleDao articleDao;
    private final ModelMapper modelMapper;

    public ArticleService(ArticleDao articleDao, ModelMapper modelMapper) {
        this.articleDao = articleDao;
        this.modelMapper = modelMapper;
    }

    public void addArticle(ArticleDto articleDto) {
        articleDto.setDate(LocalDateTime.now().format(formatter));
        ArticleVo articleVo = modelMapper.map(articleDto,ArticleVo.class);
        articleDao.save(articleVo);
    }

    public ArticleDto getArticle(int id) {
        ArticleVo articleVo = articleDao.findById(id);
        return modelMapper.map(articleVo,ArticleDto.class);
    }

    public List<ArticleDto> getArticles() {
        return articleDao.findAll().stream()
                .map(articleVo -> modelMapper.map(articleVo,ArticleDto.class))
                .collect(Collectors.toList());
    }

}
