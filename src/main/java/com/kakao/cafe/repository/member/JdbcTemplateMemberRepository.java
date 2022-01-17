package com.kakao.cafe.repository.member;

import com.kakao.cafe.domain.member.*;
import com.kakao.cafe.exception.ErrorMessages;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
@Slf4j
public class JdbcTemplateMemberRepository implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Member saveMember(Member member) {
        if (isUserIdExist(member.getUserId()) != null) {
            throw new DuplicateRequestException(ErrorMessages.DUPLICATED_USERID);
        }
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("MEMBER").usingGeneratedKeyColumns("member_id");
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(inputParameters(member)));
        member.setMemberId(key.longValue());
        log.info("{} is joined", member.getUserId().toString());
        return member;
    }

    @Override
    public List<Member> findAllMembers() {
        return jdbcTemplate.query("select * from member", memberRowMapper());
    }

    @Override
    public Member findOne(Long memberId) {
        log.info("memberId : {}", memberId);
        List<Member> result = jdbcTemplate.query("select * from member where member_id = ?", memberRowMapper(), memberId);
        Optional<Member> member = result.stream().findAny();
        if (member.isEmpty()) {
            throw new NoSuchElementException(ErrorMessages.NO_SUCH_MEMBER);
        }
        return member.get();
    }

    @Override
    public void deleteMember(Long memberId) {
        if (isMemberIdExist(memberId))
            throw new NoSuchElementException(ErrorMessages.NO_SUCH_MEMBER);
        jdbcTemplate.query("delete from member where member_id = ?", memberRowMapper(), memberId);
    }

    @Override
    public void deleteAllMembers() {
        jdbcTemplate.query("delete table member", memberRowMapper());
    }

    @Override
    public Member findByUserId(UserId userId) {
        List<Member> result = jdbcTemplate.query("select * from member where user_id = ?", memberRowMapper(), userId.getUserId());
        Optional<Member> member = result.stream().findAny();
        if (member.isEmpty()) {
            throw new NoSuchElementException(ErrorMessages.NO_SUCH_MEMBER);
        }
        return member.get();
    }

    @Override
    public Member updateByMemberId(Member member) {
        jdbcTemplate.update("update MEMBER set user_name = ?, email = ? where member_id = ?", member.getName().getName(), member.getEmail().getEmail(), member.getMemberId());
        log.info("회원정보 수정 : {}", member.getMemberId());
        return findOne(member.getMemberId());
    }

    @Override
    public Long isUserIdExist(UserId userId) {
        List<Member> result = jdbcTemplate.query("select * from member where user_id = ?", memberRowMapper(), userId.getUserId());
        if (result.stream().findAny().isPresent())
            return result.stream().findAny().get().getMemberId();
        return null;
    }

    @Override
    public boolean isMemberIdExist(Long memberId) {
        List<Member> result = jdbcTemplate.query("select * from member where member_id = ?", memberRowMapper(), memberId);
        return result.stream().findAny().isPresent();
    }

    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> new Member(
                new UserId(rs.getString("user_id")),
                new Name(rs.getString("user_name")),
                new Password(rs.getString("password")),
                new Email(rs.getString("email")),
                rs.getLong("member_id"));
    }

    private Map<String, Object> inputParameters(Member member) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("user_id", member.getUserId().getUserId());
        parameters.put("user_name", member.getName().getName());
        parameters.put("password", member.getPassword().getPassword());
        parameters.put("email", member.getEmail().getEmail());
        return parameters;
    }
}
