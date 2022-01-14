package com.kakao.cafe.repository;

import com.kakao.cafe.domain.member.*;
import com.kakao.cafe.exception.ErrorMessages;
import com.kakao.cafe.repository.member.MemberRepository;
import com.kakao.cafe.repository.member.MemoryMemberRepository;
import com.sun.jdi.request.DuplicateRequestException;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MemoryMemberRepositoryTest {

    private MemberRepository memberRepository;

    @BeforeEach
    void initSettings() {
        memberRepository = new MemoryMemberRepository();
    }

    @AfterEach
    void clearStore() {
        memberRepository.deleteAllMembers();
    }

    @Test
    @DisplayName("회원가입 테스트")
    void saveTest() {
        // given
        Member member = new Member(new UserId("rubam"), new Name("공돈욱"), new Password("K1d2345!"), new Email("wooky9633@naver.com"));

        // when
        Member savedMember = memberRepository.saveMember(member);

        // then
        assertThat(savedMember).isEqualTo(member);
    }

    @Test
    @DisplayName("중복 userId 회원가입")
    void saveDuplicatedUserIdTest() {
        // given
        Member oldMember = new Member(new UserId("rubam"), new Name("공돈욱"), new Password("K1d2345!"), new Email("wooky9633@naver.com"));
        Member newMember = new Member(new UserId("rubam"), new Name("루밤"), new Password("K1d2345!"), new Email("rubam.kong@naver.com"));
        memberRepository.saveMember(oldMember);

        // when
        String errorMessage = Assertions.assertThrows(DuplicateRequestException.class, () -> {
            memberRepository.saveMember(newMember);
        }).getMessage();
        assertThat(errorMessage).isEqualTo(ErrorMessages.DUPLICATED_USERID);
    }

    @Test
    @DisplayName("전체 회원 리스트 조회 테스트")
    void findAllMembersTest() {
        // given
        Member member1 = new Member(new UserId("wooky"), new Name("공돈욱"), new Password("K1d2345!"), new Email("wooky9633@naver.com"));
        Member member2 = new Member(new UserId("rubam"), new Name("루밤"), new Password("K1d2345!"), new Email("rubam.kong@naver.com"));
        memberRepository.saveMember(member1);
        memberRepository.saveMember(member2);

        // when
        List<Member> result = memberRepository.findAllMembers();

        // then
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("회원 조회 테스트")
    void findOneMemberTest() {
        // given
        Member inputMember = new Member(new UserId("wooky"), new Name("공돈욱"), new Password("K1d2345!"), new Email("wooky9633@naver.com"));
        Member savedMember = memberRepository.saveMember(inputMember);

        // when
        Member member = memberRepository.findOne(savedMember.getMemberId());

        // then
        assertThat(member).isEqualTo(inputMember);
    }

    @Test
    @DisplayName("존재하지 않는 회원 조회 memberId")
    void findOneMemberTest2() {
        String errorMessage = Assertions.assertThrows(NoSuchElementException.class, () -> {
            memberRepository.findOne(1L);
        }).getMessage();

        assertThat(errorMessage).isEqualTo(ErrorMessages.NO_SUCH_MEMBER);
    }

    @Test
    @DisplayName("존재하지 않는 회원 조회 userId")
    void findByUserIdTest() {
        // given
        String inputUserId = "rubam";
        UserId userId = new UserId(inputUserId);

        // then
        String errorMessage = Assertions.assertThrows(NoSuchElementException.class, () -> {
            memberRepository.findByUserId(userId);
        }).getMessage();

        assertThat(errorMessage).isEqualTo(ErrorMessages.NO_SUCH_MEMBER);
    }
}
