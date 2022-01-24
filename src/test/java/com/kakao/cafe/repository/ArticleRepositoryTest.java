package com.kakao.cafe.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

//@JdbcTest
public class ArticleRepositoryTest {
    /*
    private final int TEST_ARTICLE_COUNT = 5;
    private ArticleRepository articleRepository;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ArticleRepositoryTest(JdbcTemplate jdbcTemplate) {
        this.articleRepository = new ArticleDao(jdbcTemplate);
        this.jdbcTemplate = jdbcTemplate;
    }

    @BeforeEach
    public void setUp() {
        String sql = String.format("insert into %s (%s, %s, %s) values (?, ?, ?)",
                ArticleDBConstants.TABLE_NAME,
                ArticleDBConstants.COLUMN_WRITER,
                ArticleDBConstants.COLUMN_TITLE,
                ArticleDBConstants.COLUMN_CONTENTS);

        for (int i = 0; i < TEST_ARTICLE_COUNT; i++) {
            jdbcTemplate.update(sql, "id"+i, "title"+i, "contents"+i);
        }
    }

    @AfterEach
    public void tearDown() {
        String sql = String.format("truncate table %s", ArticleDBConstants.TABLE_NAME);
        jdbcTemplate.execute(sql);
    }

    @DisplayName("게시글 저장 테스트")
    @Test
    public void saveTest() throws SQLException {
        Article article = new Article(0, "id0","newArticle", "contents");

        articleRepository.save(article);

        // ?
    }

    @DisplayName("게시글 목록 반환 테스트")
    @Test
    public void findAllTest() {
        List<Article> articleList = articleRepository.findAll();

        assertThat(articleList.size()).isEqualTo(TEST_ARTICLE_COUNT);
    }

    @DisplayName("id로 게시글 찾기 테스트 - 존재하는 case")
    @Test
    public void findByIdTest() throws SQLException {
        // given
        Article newArticle = new Article(0, "id0","newArticle", "contents");
        int id;
        id = articleRepository.save(newArticle);

        // when
        Article article = articleRepository.findById(id);

        // then
        assertThat(article.getId()).isEqualTo(id);
    }

    @DisplayName("id로 게시글 찾기 테스트 - 존재하지 않는 case(음수 id)")
    @Test
    public void findByIdNotExistTest() {
        int id = -1;

        assertThrows(NoSuchElementException.class, () -> {
            articleRepository.findById(id);
        });
    }

     */
}
