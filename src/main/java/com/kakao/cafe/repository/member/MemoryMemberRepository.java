package com.kakao.cafe.repository.member;

import com.kakao.cafe.domain.member.Member;
import com.kakao.cafe.domain.member.UserId;
import com.kakao.cafe.exception.ErrorMessages;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

//@Repository
@RequiredArgsConstructor
@Slf4j
public class MemoryMemberRepository implements MemberRepository {

    private static final Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public Member saveMember(Member member) {
        if (isUserIdExist(member.getUserId()) != null)
            throw new DuplicateRequestException(ErrorMessages.DUPLICATED_USERID);
        Member newMember = new Member(member.getUserId(), member.getName(), member.getPassword(), member.getEmail(), ++sequence);
        store.put(newMember.getMemberId(), newMember);
        return newMember;
    }

    @Override
    public List<Member> findAllMembers() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Member findOne(Long memberId) {
        if (!isMemberIdExist(memberId)) {
            throw new NoSuchElementException(ErrorMessages.NO_SUCH_MEMBER);
        }
        return store.get(memberId);
    }

    @Override
    public void deleteMember(Long memberId) {
        if (!isMemberIdExist(memberId))
            throw new NoSuchElementException(ErrorMessages.NO_SUCH_MEMBER);
        store.remove(memberId);
    }

    @Override
    public void deleteAllMembers() {
        store.clear();
    }

    @Override
    public Member findByUserId(UserId userId) {
        Long memberId = isUserIdExist(userId);
        if (memberId == null) {
            throw new NoSuchElementException(ErrorMessages.NO_SUCH_MEMBER);
        }
        return store.get(memberId);
    }

    @Override
    public boolean isMemberIdExist(Long memberId) {
        return store.get(memberId) != null;
    }

    @Override
    public Member updateByMemberId(Member member) {
        Member updateMember = store.get(member.getMemberId());
        store.put(updateMember.getMemberId(), new Member(updateMember.getUserId(), member.getName(), updateMember.getPassword(), member.getEmail(), updateMember.getMemberId()));
        return store.get(updateMember.getMemberId());
    }

    @Override
    public Long isUserIdExist(UserId userId) {
        for (Long id : store.keySet()) {
            if (store.get(id).getUserId().equals(userId))
                return id;
        }
        return null;
    }
}
