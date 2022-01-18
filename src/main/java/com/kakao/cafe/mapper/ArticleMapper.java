package com.kakao.cafe.mapper;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.ArticleFormRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper()
public interface ArticleMapper extends RowMapper<Article> {

    ArticleMapper INSTANCE = Mappers.getMapper( ArticleMapper.class );

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "writer", ignore = true)
    @Mapping(target = "time", ignore = true)
    Article toEntity(ArticleFormRequest articleFormRequest);

    @Override
    default Article mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Article.builder().
                id(rs.getLong("id")).
                writer(rs.getString("writer")).
                title(rs.getString("title")).
                contents(rs.getString("contents")).
                time(rs.getString("time")).
                build();
    }
}