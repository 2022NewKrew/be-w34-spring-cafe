package com.kakao.cafe.member.repository;

import com.kakao.cafe.member.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MemberRepository {

    Member save(Member member);

    Member findOne(Long id);

    Member findByEmail(String email);

    List<Member> findAll();

    void update(Long id, Member member);

}
