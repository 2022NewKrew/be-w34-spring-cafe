package com.kakao.cafe.repository;

import com.kakao.cafe.DB.RogerDB;
import com.kakao.cafe.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MemberRepositoryImpl implements MemberRepository {
    private final RogerDB rogerDB;

    @Autowired
    public MemberRepositoryImpl(RogerDB rogerDB) {
        this.rogerDB = rogerDB;
    }

    @Override
    public int save(Member newMember) {
        rogerDB.getUser().add(newMember);
        return 0;
    }

    @Override
    public Optional<List<Member>> findAll() {
        return Optional.ofNullable(rogerDB.getUser());
    }

    @Override
    public Member findByUserId(String userId) {
        return rogerDB.getUser()
                .stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst().orElse(null);
    }

    @Override
    public Member findByEmail(String email) {
        return rogerDB.getUser()
                .stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst().orElse(null);
    }
}
