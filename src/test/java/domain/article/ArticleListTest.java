package domain.article;

import com.kakao.cafe.service.ArticleService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArticleListTest {

    ArticleList articleList = ArticleList.getInstance();

    @Test
    void size() {
        add();
        assertEquals(articleList.size(),1);
    }

    @Test
    void add() {
        articleList.add(new Article("Spring이란 무엇인가요?","자세히 설명해주세요."));
    }
}