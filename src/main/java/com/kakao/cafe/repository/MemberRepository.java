package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    int save(Member newMember);

    Optional<List<Member>> findAll();

    Member findByUserId(String userId);

    Member findByEmail(String email);
}
