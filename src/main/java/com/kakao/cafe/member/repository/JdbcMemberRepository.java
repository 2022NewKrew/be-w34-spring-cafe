package com.kakao.cafe.member.repository;

import com.kakao.cafe.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcMemberRepository implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Member save(Member member) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO MEMBER (EMAIL, NICKNAME, PASSWORD, CREATE_DATE) VALUES(?, ?, ?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, member.getEmail());
            ps.setString(2, member.getNickname());
            ps.setString(3, member.getPassword());
            ps.setObject(4, member.getCreateDate());
            return ps;
        }, keyHolder);

        Number id = keyHolder.getKey();

        if (id != null) {
            member.setId(id.longValue());
        }
        return member;
    }

    @Override
    public Optional<Member> findOne(Long id) {
        String sql = "SELECT ID, EMAIL, NICKNAME, PASSWORD, CREATE_DATE FROM MEMBER WHERE ID = ?";
        return jdbcTemplate.query(sql, memberRowMapper(), id).stream().findAny();
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        String sql = "SELECT ID, EMAIL, NICKNAME, PASSWORD, CREATE_DATE FROM MEMBER WHERE EMAIL = ?";
        return jdbcTemplate.query(sql, memberRowMapper(), email).stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        String sql = "SELECT ID, EMAIL, NICKNAME, PASSWORD, CREATE_DATE FROM MEMBER";
        return jdbcTemplate.query(sql, memberRowMapper());
    }

    @Override
    public void update(Long id, Member member) {
        String sql = "UPDATE MEMBER SET NICKNAME = ?, PASSWORD = ? WHERE ID = ?";
        jdbcTemplate.update(sql, member.getNickname(), member.getPassword(), id);
    }

    private RowMapper<Member> memberRowMapper() {
        return ((rs, rowNum) -> Member.builder()
                .id(rs.getLong("ID"))
                .email(rs.getString("EMAIL"))
                .nickname(rs.getString("NICKNAME"))
                .password(rs.getString("PASSWORD"))
                .createDate(rs.getObject("CREATE_DATE", LocalDate.class))
                .build());
    }
}
