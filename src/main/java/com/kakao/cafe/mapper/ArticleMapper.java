package com.kakao.cafe.mapper;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.service.ArticleService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper(componentModel="spring", uses= ArticleService.class)
public interface ArticleMapper extends RowMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "time", ignore = true)
    Article toEntity(ArticleDto user);

}