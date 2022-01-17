package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    /**
     * userId가 매개변수로 들어온 userId와 같은 사용자를 찾는 메서드
     * @param userId: 찾을 사용자의 userId
     * @return Optional<User>: Optional 로 감싸진 User 인스턴스
     */
    public Optional<User> findByUserId(String userId) {
        String sql = "select * from user_table where user_id = ?";

        return this.readOneQuery(sql, userId);
    }

    /**
     * 새로운 유저를 만드는(Create) 메서드
     * @param newUser: 만들 User 정보(Entity)
     * @return int: 영향받은 행의 수(1)
     */
    public int save(User newUser) {
        String sql = "insert into user_table(user_id, password, name, email) values(?, ?, ?, ?)";

        return this.writeQuery(
                sql,
                newUser.getUserId(),
                newUser.getPassword(),
                newUser.getName(),
                newUser.getEmail()
        );
    }

    /**
     * 모든 유저를 반환하는 메서드
     * @return List<User>: User 인스턴스로 이루어진 리스트
     */
    public List<User> findAll() {
        String sql = "select * from user_table";

        return this.readListQuery(sql);
    }

    /**
     * ID(PK)가 매개변수로 들어온 id와 같은 사용자를 반환하는 메서드
     * @param id: 찾을 사용자의 ID(PK)
     * @return Optional<User>: Optional 로 감싸진 User 인스턴스
     */
    public Optional<User> findById(Long id) {
        String sql = "select * from user_table where id = ?";

        return this.readOneQuery(sql, id);
    }

    /**
     * 사용자의 정보를 수정하는 메서드
     * 패스워드, 이름, 이메일만 변경이 가능하다.
     * 만약 패스워드, 이름, 이메일이 빈 값("")이면 수정하지 않는다.
     * @param user: 수정할 사용자의 정보(Entity)
     * @return int: 영향받은 행의 개수(1)
     */
    public int update(User user) {
        String sql = "update user_table set password = ?, name = ?, email = ? where id = ?";

        return this.writeQuery(
                sql,
                user.getPassword(),
                user.getName(),
                user.getEmail(),
                user.getId());
    }

    /**
     * 로그인 하려는 정보에 해당하는 User 정보 반환
     * @param userId: 유저의 userId
     * @param password: 유저의 비밀번호
     * @return Optional<User>: Optional 로 감싸진 User 인스턴스
     */
    public Optional<User> findByUserIdAndPassword(String userId, String password) {
        String sql = "select * from user_table where user_id = ? and password = ?";

        return this.readOneQuery(
                sql,
                userId,
                password
        );
    }

    /**
     * 단일 행을 반환하는 SELECT 문을 담당하는 메서드
     * @param sql: 실행하고자 하는 SQL
     * @param parameters: SQL 문에 들어갈 매개변수(가변인자)
     * @return Optional<User>: Optional로 감싸진 User 인스턴스
     */
    private Optional<User> readOneQuery(String sql, Object... parameters) {
        List<User> user = this.readListQuery(sql, parameters);

        return Optional.ofNullable(user.isEmpty() ? null : user.get(0));
    }

    /**
     * SELECT 문을 담당하는 메서드
     * @param sql: 실행하고자 하는 SQL
     * @param parameters: SQL 문에 들어갈 매개변수(가변인자)
     * @return List<User>: User 인스턴스로 이루어진 리스트
     */
    private List<User> readListQuery(String sql, Object... parameters) {
        return this.jdbcTemplate.query(
                sql,
                (rs, rowNum) -> new User(
                        rs.getLong("id"),
                        rs.getString("user_id"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email"),
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
