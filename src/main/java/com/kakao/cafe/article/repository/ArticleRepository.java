package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.entity.Article;
import com.kakao.cafe.reply.repository.ReplyRepository;
import com.kakao.cafe.user.exception.UserNotFoundException;
import com.kakao.cafe.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ArticleRepository {

    private final JdbcTemplate jdbcTemplate;
    private final UserRepository userRepository;
    private final ReplyRepository replyRepository;

    /**
     * 새로운 게시글 만드는(Create) 메서드
     * @param article: 만들 게시글 정보(Entity)
     * @return int: 영향받은 행의 수(1)
     */
    public int save(Article article) {
        String sql = "insert into article_table(writer_id, title, contents) values(?, ?, ?)";

        return this.writeQuery(
                sql,
                article.getWriter().getId(),
                article.getTitle(),
                article.getContents()
        );
    }

    /**
     * 모든 게시글를 반환하는 메서드
     * @return List<Article>: Article 인스턴스로 이루어진 리스트
     */
    public List<Article> findAll() {
        String sql = "select * from article_table where tombstone = false";

        return this.readListQuery(sql);
    }

    /**
     * ID(PK)가 매개변수로 들어온 id와 같은 게시글 반환하는 메서드
     * @param id: 찾을 게시글의 ID(PK)
     * @return Optional<Article>: Optional 로 감싸진 Article 인스턴스
     */
    public Optional<Article> findById(Long id) {
        String sql = "select * from article_table where id = ? and tombstone = false";

        return this.readOneQuery(sql, id);
    }

    /**
     * 게시글의 정보를 수정하는 메서드
     * 제목, 내용만 변경이 가능하다.
     * 만약 제목, 내용이 빈 값("")이면 수정하지 않는다.
     * @param article: 수정할 게시글의 정보(Entity)
     * @return int: 영향받은 행의 개수(1)
     */
    public int update(Article article) {
        String sql = "update article_table set title = ?, contents = ? where id = ?";

        return this.writeQuery(
                sql,
                article.getTitle(),
                article.getContents(),
                article.getId());
    }

    /**
     * 게시글을 삭제하는 메서드
     * @param article - 삭제할 게시글
     * @return int: 영향받은 행의 개수(1)
     */
    public int delete(Article article) {
        String sql = "update article_table set tombstone = true where id = ?";

        return this.writeQuery(
                sql,
                article.getId()
        );
    }

    /**
     * 단일 행을 반환하는 SELECT 문을 담당하는 메서드
     * @param sql: 실행하고자 하는 SQL
     * @param parameters: SQL 문에 들어갈 매개변수(가변인자)
     * @return Optional<Article>: Optional로 감싸진 Article 인스턴스
     */
    private Optional<Article> readOneQuery(String sql, Object... parameters) {
        List<Article> user = this.readListQuery(sql, parameters);

        return Optional.ofNullable(user.isEmpty() ? null : user.get(0));
    }

    /**
     * SELECT 문을 담당하는 메서드
     * @param sql: 실행하고자 하는 SQL
     * @param parameters: SQL 문에 들어갈 매개변수(가변인자)
     * @return List<Article>: Article 인스턴스로 이루어진 리스트
     */
    private List<Article> readListQuery(String sql, Object... parameters) {
        return this.jdbcTemplate.query(
                sql,
                (rs, rowNum) -> new Article(
                        rs.getLong("id"),
                        this.userRepository.findById(rs.getLong("writer_id")).orElseThrow(UserNotFoundException::new),
                        rs.getString("title"),
                        rs.getString("contents"),
                        this.replyRepository.findByArticleId(rs.getLong("id")),
                        rs.getTimestamp("created_at").toLocalDateTime()
                ), parameters
        );
    }

    /**
     * INSERT, DELETE, UPDATE 문을 담당하는 메서드
     * @param sql: 실행하고자 하는 SQL
     * @param parameters: SQL 문에 들어갈 매개변수(가변인자)
     * @return int: 영향받은 행의 개수(1)
     */
    private int writeQuery(String sql, Object... parameters) {
        return this.jdbcTemplate.update(
                sql, parameters
        );
    }
}
