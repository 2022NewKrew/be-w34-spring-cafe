package com.kakao.cafe.repository;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.Text;
import com.kakao.cafe.domain.article.Time;
import com.kakao.cafe.domain.article.Title;
import com.kakao.cafe.domain.member.*;
import com.kakao.cafe.exception.ErrorMessages;
import com.kakao.cafe.repository.article.ArticleRepository;
import com.kakao.cafe.repository.article.MemoryArticleRepository;
import com.kakao.cafe.repository.member.MemberRepository;
import com.kakao.cafe.repository.member.MemoryMemberRepository;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MemoryArticleRepositoryTest {

    private ArticleRepository articleRepository;
    private MemberRepository memberRepository;
    private Member member;

    @BeforeEach
    void initSettings() {
        articleRepository = new MemoryArticleRepository();
        memberRepository = new MemoryMemberRepository();
        member = new Member(new UserId("rubam"), new Name("rubam"), new Password("Kdw12345!"), new Email("wooky9633@naver.com"));
        memberRepository.saveMember(member);
    }

    @AfterEach
    void deleteStore() {
        articleRepository.deleteAllArticle();
        memberRepository.deleteAllMembers();
    }

    @Test
    @DisplayName("게시글 등록 테스트")
    void saveArticleTest() {
        // given
        Title title = new Title("title");
        Text text = new Text("text");

        Article article = new Article(title, text, member, Time.now());

        // when
        Article savedArticle = articleRepository.save(article);

        // then
        assertThat(savedArticle).isEqualTo(article);
    }

    @Test
    @DisplayName("게시글 검색 테스트")
    void findOneArticleTest() {
        // given
        Title title = new Title("title");
        Text text = new Text("text");
        Article article = new Article(title, text, member, Time.now());
        Article savedArticle = articleRepository.save(article);

        // when
        Article found = articleRepository.findArticle(savedArticle.getArticleId());

        // then
        assertThat(found).isEqualTo(article);
    }

    @Test
    @DisplayName("존재하지 않는 게시글 검색")
    void findNotExistArticle() {
        String errorMessage = Assertions.assertThrows(NoSuchElementException.class, () -> {
            articleRepository.findArticle(1L);
        }).getMessage();
        assertThat(errorMessage).isEqualTo(ErrorMessages.NO_SUCH_ARTICLE);
    }

    @Test
    @DisplayName("모든 게시글 검색")
    void findAllArticleTest() {
        // given
        Title title = new Title("title");
        Text text = new Text("text");
        Article article = new Article(title, text, member, Time.now());

        Title title1 = new Title("title2");
        Text text1 = new Text("text2");
        Article article1 = new Article(title1, text1, member, Time.now());

        articleRepository.save(article);
        articleRepository.save(article1);

        // when
        List<Article> articles = articleRepository.findAllArticle();

        // then
        assertThat(articles.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("게시글 삭제 테스트")
    void deleteArticleTest() {
        // given
        Title title = new Title("title");
        Text text = new Text("text");
        Article article = new Article(title, text, member, Time.now());
        Article savedArticle = articleRepository.save(article);

        // when
        articleRepository.deleteArticle(savedArticle.getArticleId());

        // then
        String errorMessage = Assertions.assertThrows(NoSuchElementException.class, () -> {
            articleRepository.findArticle(savedArticle.getArticleId());
        }).getMessage();
        assertThat(errorMessage).isEqualTo(ErrorMessages.NO_SUCH_ARTICLE);
    }

    @Test
    @DisplayName("존재하지 않는 게시글 삭제 테스트")
    void deleteNotExistArticleTest() {
        String errorMessage = Assertions.assertThrows(NoSuchElementException.class, () -> {
            articleRepository.deleteArticle(1L);
        }).getMessage();
        assertThat(errorMessage).isEqualTo(ErrorMessages.NO_SUCH_ARTICLE);
    }
}
