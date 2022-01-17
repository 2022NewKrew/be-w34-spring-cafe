package com.kakao.cafe.service;

import com.kakao.cafe.domain.member.*;
import com.kakao.cafe.exception.ErrorMessages;
import com.kakao.cafe.repository.member.MemberRepository;
import com.kakao.cafe.service.member.MemberServiceV1;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MemberServiceV1Test {

    private static Member member;
    @Mock
    private MemberRepository memberRepository;
    @InjectMocks
    private MemberServiceV1 memberService;

    @BeforeEach
    void initiateMemberRepository() {
        member = new Member(new UserId("rubam"), new Name("공돈욱"), new Password("Ab12345!"), new Email("wooky9633@naver.com"), null);
    }

    @Test
    @DisplayName("회원가입 테스트")
    void joinMemberTest() {
        // given
        when(memberRepository.saveMember(member))
                .thenReturn(new Member(
                        new UserId("rubam"),
                        new Name("공돈욱"),
                        new Password("Ab12345!"),
                        new Email("wooky9633@naver.com"),
                        1L
                ));

        // when
        Member savedMember = memberService.joinMember(member);

        // then
        assertThat(member).isEqualTo(savedMember);
    }

    @Test
    @DisplayName("회원정보 수정 테스트")
    void editMemberTest() {
        // given
        Member editMember = new Member(new UserId("rubam"), new Name("루밤"), new Password("Ab12345!"), new Email("rubam@naver.com"), 1L);
        when(memberRepository.updateByMemberId(editMember))
                .thenReturn(new Member(
                        new UserId("rubam"),
                        new Name("루밤"),
                        new Password("Ab12345!"),
                        new Email("rubam@naver.com"),
                        1L));
        when(memberRepository.findOne(editMember.getMemberId()))
                .thenReturn(member);

        // when
        Member updatedMember = memberService.joinMember(editMember);

        // then
        assertThat(updatedMember.getName()).isEqualTo(editMember.getName());
        assertThat(updatedMember.getEmail()).isEqualTo(editMember.getEmail());
    }

    @Test
    @DisplayName("잘못된 비밀번호로 회원정보 수정 테스트")
    void wrongPasswordTest() {
        // given
        String wrongPassword = "Abc12345!";
        Member editMember = new Member(new UserId("rubam"), new Name("루밤"), new Password(wrongPassword), new Email("rubam@naver.com"), 1L);
        when(memberRepository.findOne(editMember.getMemberId()))
                .thenReturn(member);

        // then
        String errorMessage = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            memberService.joinMember(editMember);
        }).getMessage();
        assertThat(errorMessage).isEqualTo(ErrorMessages.LOGIN_FAILED);
    }
}
