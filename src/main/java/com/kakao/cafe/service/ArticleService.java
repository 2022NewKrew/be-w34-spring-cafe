package com.kakao.cafe.service;

import com.kakao.cafe.dao.ArticleDao;
import com.kakao.cafe.dto.article.ArticleDto;
import com.kakao.cafe.vo.ArticleVo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final ArticleDao articleDao;

    public ArticleService(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    public void addArticle(ArticleDto articleDto) {
        articleDto.setDate(LocalDateTime.now().format(formatter));
        ArticleVo articleVo = dtoToVo(articleDto);
        articleDao.save(articleVo);
    }

    public ArticleDto getArticle(int id) {
        ArticleVo articleVo = articleDao.findById(id);
        return voToDto(articleVo);
    }

    public List<ArticleDto> getArticles() {
        return articleDao.findAll().stream()
                .map(this::voToDto)
                .collect(Collectors.toList());
    }

    private ArticleDto voToDto(ArticleVo articleVo) {
        ArticleDto articleDto = new ArticleDto();
        articleDto.setId(articleVo.getId());
        articleDto.setWriter(articleVo.getWriter());
        articleDto.setTitle(articleVo.getTitle());
        articleDto.setContents(articleVo.getContents().replace("\r\n","<br>"));
        articleDto.setDate(articleVo.getDate());
        return articleDto;
    }

    private ArticleVo dtoToVo(ArticleDto articleDto) {
        return new ArticleVo(articleDto.getId(), articleDto.getWriter(), articleDto.getTitle(), articleDto.getContents(), articleDto.getDate());
    }
}
