package com.kakao.cafe.repository.member;

import com.kakao.cafe.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class MemoryMemberRepository implements MemberRepository {

    private static final Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public void saveMember(Member member) {
        member.setMemberId(++sequence);
        store.put(member.getMemberId(), member);
    }

    @Override
    public List<Member> findAllMembers() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Member> findOne(Long memberId) {
        return Optional.ofNullable(store.get(memberId));
    }

    @Override
    public void deleteMember(Long id) {
        store.remove(id);
    }

    @Override
    public void deleteAllMembers() {
        store.clear();
    }
}
