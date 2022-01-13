package com.kakao.cafe.member.repository;

import com.kakao.cafe.member.domain.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemoryMemberRepository implements MemberRepository {

    private static long sequenceNumber = 0;
    private static final List<Member> memoryRepository = new ArrayList<>();

    @Override
    public Member save(Member member) {
        sequenceNumber++;
        member.setId(sequenceNumber);
        memoryRepository.add(member);
        return member;
    }

    @Override
    public Optional<Member> findOne(Long id) {
        return Optional.ofNullable(memoryRepository.get(id.intValue() - 1));
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return Optional.ofNullable(memoryRepository.stream()
                .filter(member -> member.getEmail().equals(email))
                .findAny()
                .orElse(null));
    }

    @Override
    public List<Member> findAll() {
        return memoryRepository;
    }

    @Override
    public void update(Long id, Member member) {
        member.setId(id);
        System.out.println(member);
        memoryRepository.set(id.intValue() - 1, member);
    }
}
