package com.kakao.cafe.member.controller;

import com.kakao.cafe.member.domain.Member;
import com.kakao.cafe.member.dto.MemberRequestDTO;
import com.kakao.cafe.member.dto.MemberUpdateRequestDTO;
import com.kakao.cafe.member.repository.MemberRepository;
import com.kakao.cafe.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberService memberService;

    @ParameterizedTest
    @MethodSource("validMemberRequest")
    @DisplayName("정상적인 회원가입은 성공해야 한다.")
    void postMembers(MemberRequestDTO memberRequestDTO) throws Exception {
        mockMvc.perform(post("/members")
                        .flashAttr("memberRequestDTO", memberRequestDTO)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection());
    }

    static Stream<Arguments> validMemberRequest() {
        return Stream.of(
                Arguments.of(new MemberRequestDTO("flip1@kakao.com", "name1", "1", "1")),
                Arguments.of(new MemberRequestDTO("flip2@kakao.com", "name2", "12", "12")),
                Arguments.of(new MemberRequestDTO("flip3@kakao", "name3", "1234", "1234"))
        );
    }

    @ParameterizedTest
    @MethodSource("duplicateMemberRequest")
    @DisplayName("중복된 이메일을 가진 회원은 가입할 수 없다.")
    void duplicateMemberRequestTest(MemberRequestDTO memberRequestDTO) throws Exception {
        // given
        MemberRequestDTO originMember = new MemberRequestDTO("flip@kakao.com", "name", "pass", "pass");

        mockMvc.perform(post("/members")
                        .flashAttr("memberRequestDTO", originMember)
                        .contentType(MediaType.APPLICATION_JSON));

        // when
        // then
        mockMvc.perform(post("/members")
                        .flashAttr("memberRequestDTO", memberRequestDTO)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(model().attributeExists("errorMessage"))
                .andExpect(model().attribute("errorMessage", "이미 존재하는 이메일입니다."))
                .andExpect(view().name("/error"));
    }

    static Stream<Arguments> duplicateMemberRequest() {
        return Stream.of(
                Arguments.of(new MemberRequestDTO("flip@kakao.com", "name1", "1", "1")),
                Arguments.of(new MemberRequestDTO("flip@kakao.com", "name2", "12", "12")),
                Arguments.of(new MemberRequestDTO("flip@kakao.com", "name3", "1234", "1234"))
        );
    }

    @Test
    @DisplayName("회원 정보를 수정할 수 있다.")
    void validMemberUpdateTest() throws Exception {
        // given
        MemberRequestDTO originMember = new MemberRequestDTO("flip@kakao.com", "name", "pass", "pass");
        Long memberId = memberService.join(originMember);

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("loginMemberDTO", memberService.findOne(memberId));

        MemberUpdateRequestDTO memberUpdateRequestDTO = new MemberUpdateRequestDTO("flip@kakao.com", "name", "pass", "1", "1");

        // when
        // then
        mockMvc.perform(put("/members/" + memberId)
                        .session(session)
                        .flashAttr("memberUpdateRequestDTO", memberUpdateRequestDTO)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/members"));
    }

    @ParameterizedTest
    @MethodSource("invalidMemberUpdate")
    @DisplayName("현재 비밀번호가 틀리면 회원정보를 수정할 수 없다.")
    void invalidMemberUpdateTest(MemberUpdateRequestDTO memberUpdateRequestDTO) throws Exception {
        // given
        MemberRequestDTO originMember = new MemberRequestDTO("flip@kakao.com", "name", "pass", "pass");
        Long memberId = memberService.join(originMember);

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("loginMemberDTO", memberService.findOne(memberId));

        // when
        // then
        mockMvc.perform(put("/members/" + memberId)
                        .session(session)
                        .flashAttr("memberUpdateRequestDTO", memberUpdateRequestDTO)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(model().attributeExists("errorMessage"))
                .andExpect(model().attribute("errorMessage", "비밀번호가 일치하지 않습니다."))
                .andExpect(view().name("/error"));
    }

    static Stream<Arguments> invalidMemberUpdate() {
        return Stream.of(
                Arguments.of(new MemberUpdateRequestDTO("flip@kakao.com", "name1", "1", "12", "12")),
                Arguments.of(new MemberUpdateRequestDTO("flip@kakao.com", "name1", "121", "12", "12")),
                Arguments.of(new MemberUpdateRequestDTO("flip@kakao.com", "name1", "pass1", "12", "12"))
        );
    }
}
