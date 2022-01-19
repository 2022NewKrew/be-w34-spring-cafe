package com.kakao.cafe.article.service;

import com.kakao.cafe.article.dto.ArticleDto;
import com.kakao.cafe.article.entity.Article;
import com.kakao.cafe.article.mapper.ArticleMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ArticleFindServiceTest {

    @Mock
    private ArticleFindService articleFindService;

    @Autowired
    private ArticleMapper articleMapper = Mappers.getMapper(ArticleMapper.class);

    private final ArticleDto givenArticleDto =
            ArticleDto.builder()
                    .id(1l)
                    .title("제목")
                    .content("내용")
                    .createTime(LocalDateTime.now())
                    .build();

    private final Article givenArticle = articleMapper.toEntity(givenArticleDto);

    @Test
    @DisplayName("게시글 검색 성공")
    void test1() {
        // given
        given(articleFindService.getArticle(1l)).willReturn(givenArticleDto);

        // when
        ArticleDto foundArticleDto = articleFindService.getArticle(1l);

        // then
        assertThat(givenArticle.getId()).isEqualTo(foundArticleDto.getId());
        assertThat(givenArticle.getTitle()).isEqualTo(foundArticleDto.getTitle());
        assertThat(givenArticle.getContent()).isEqualTo(foundArticleDto.getContent());
        assertThat(givenArticle.getCreateTime()).isEqualTo(foundArticleDto.getCreateTime());
    }

    @Test
    @DisplayName("게시글 리스트 조회 성공")
    void test2() {
        // given
        List<ArticleDto> givenArticleDtoList = new ArrayList<>();
        givenArticleDtoList.add(givenArticleDto);

        given(articleFindService.getArticleList()).willReturn(givenArticleDtoList);

        // when
        List<ArticleDto> foundArticleDtoList = articleFindService.getArticleList();

        // then
        assertThat(foundArticleDtoList.size()).isEqualTo(givenArticleDtoList.size());
    }
}
