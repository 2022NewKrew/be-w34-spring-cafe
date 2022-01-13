package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.ArticleDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ArticleNoBdUseRepositoryTest {
    ArticleNoBdUseRepository repository = new ArticleNoBdUseRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    @DisplayName("DB에 저장")
    void save() {
        ArticleDTO articleDTO = new ArticleDTO("aa", "aa", "aa");

        Article savedArticle = repository.save(articleDTO);
        Article result = repository.findById(articleDTO.getId()).get();
        assertThat(savedArticle).isEqualTo(result);
    }

    @Test
    @DisplayName("userId로 데이터 조회")
    void findById() {
        ArticleDTO articleDTO = new ArticleDTO("aa", "aa", "aa");

        Article savedArticle = repository.save(articleDTO);

        Article result = repository.findById(0).get();

        assertThat(result).isEqualTo(savedArticle);
    }

    @Test
    @DisplayName("전체 데이터 조회")
    void findAll() {
        ArticleDTO articleDTO = new ArticleDTO("aa", "aa", "aa");
        repository.save(articleDTO);


        ArticleDTO articleDTO2 = new ArticleDTO("bb", "bb", "bb");
        repository.save(articleDTO2);

        List<Article> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
