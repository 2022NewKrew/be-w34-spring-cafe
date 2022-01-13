package com.kakao.cafe.member.repository;

import com.kakao.cafe.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcMemberRepository implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Member save(Member member) {
        String sql = "INSERT INTO MEMBER (EMAIL, NICKNAME, PASSWORD, CREATE_DATE) VALUES (?, ?, ?, ?)";
        Long id = (long) jdbcTemplate.update(sql, member.getEmail(), member.getNickname(), member.getPassword(), member.getCreateDate());
        member.setId(id);
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
