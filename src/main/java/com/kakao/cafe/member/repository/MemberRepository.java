package com.kakao.cafe.member.repository;

import com.kakao.cafe.member.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);

    Optional<Member> findOne(Long id);

    Optional<Member> findByEmail(String email);

    List<Member> findAll();

    void update(Long id, Member member);

}
