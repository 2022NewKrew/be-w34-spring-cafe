package com.kakao.cafe.mapper;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.ArticleShowDTO;
import com.kakao.cafe.dto.ArticleShowListDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {
    String insert = "INSERT INTO article(author, title, content, postTime) values(#{article.author}, #{article.title}, #{article.content}, #{article.postTime})";
    String selectAll = "SELECT * FROM article";
    String selectAllForList = "SELECT key, author, title, postTime FROM article ORDER BY postTime DESC";
    String selectByKeyForShow = "SELECT author, title, content, postTime FROM article WHERE key = #{key}";


    @Select(selectAll)
    List<Article> selectAll();

    @Select(selectAllForList)
    List<ArticleShowListDTO> selectAllForList();

    @Select(selectByKeyForShow)
    ArticleShowDTO selectByKeyForShow(long key);

    @Insert(insert)
    @Options(useGeneratedKeys = true, keyProperty = "key")
    long insert(@Param("article") Article article);
}
