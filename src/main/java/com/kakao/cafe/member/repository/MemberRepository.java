package com.kakao.cafe.member.repository;

import com.kakao.cafe.member.domain.Member;

import java.util.List;

public interface MemberRepository {

    Long save(Member member);

    Member findOne(Long id);

    Member findByUserId(String userId);

    List<Member> findAll();

    void update(Long id);

}
