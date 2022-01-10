package com.kakao.cafe.member.repository;

import com.kakao.cafe.member.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static long sequenceNumber = 0;
    private static final HashMap<Long, Member> memoryRepository = new HashMap<>();

    @Override
    public Long save(Member member) {
        sequenceNumber++;
        member.setId(sequenceNumber);
        memoryRepository.put(sequenceNumber, member);
        return sequenceNumber;
    }

    @Override
    public Member findOne(Long id) {
        return memoryRepository.get(id);
    }

    @Override
    public Member findByUserId(String userId) {
        return memoryRepository.values().stream()
                .filter(member -> member.getUserId().equals(userId))
                .findAny()
                .orElse(null);
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(memoryRepository.values());
    }

    @Override
    public void update(Long id) {

    }
}
