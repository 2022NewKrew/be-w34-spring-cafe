package com.kakao.cafe.controller;

import com.kakao.cafe.config.Mapper;
import com.kakao.cafe.domain.member.*;
import com.kakao.cafe.dto.InquireMemberDto;
import com.kakao.cafe.service.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(MemberController.class)
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;
    @MockBean
    private Mapper mapper;

    @Test
    @DisplayName("사용자 회원가입 후 리다이렉션 테스트")
    void joinMemberTest() throws Exception {
        mockMvc.perform(
                        post("/users/create")
                                .content("password=" + "Ab12345!" + "&name=" + "david" + "&email=" + "wooky9633@naver.com" + "&userId=" + "rubam")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName("회원 목록 조회 테스트")
    void inquireAllMembersTest() throws Exception {
        // given
        UserId userId = new UserId("rubam");
        Name name = new Name("david");
        Password password = new Password("Ab12345!");
        Email email = new Email("wooky9633@naver.com");
        Member member = new Member(userId, name, password, email);

        InquireMemberDto memberDto = new InquireMemberDto(1L, userId.getUserId(), name.getName(), email.getEmail());
        List<InquireMemberDto> list = new ArrayList<>();
        list.add(memberDto);

        List<Member> members = new ArrayList<>();
        members.add(member);

        given(memberService.inquireAllMembers()).willReturn(members);
        given(mapper.map(member)).willReturn(memberDto);

        // then
        mockMvc.perform(
                        get("/users"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("members", list))
                .andExpect(view().name("user/user-list"));
    }

    @Test
    @DisplayName("회원 한명 프로필 조회")
    void inquireSpecificMemberTest() throws Exception {
        // given
        UserId userId = new UserId("rubam");
        Name name = new Name("david");
        Password password = new Password("Ab12345!");
        Email email = new Email("wooky9633@naver.com");
        Member member = new Member(userId, name, password, email, 1L);
        InquireMemberDto memberDto = new InquireMemberDto(1L, userId.getUserId(), name.getName(), email.getEmail());

        given(memberService.inquireOneMember(1L)).willReturn(member);
        given(mapper.map(member)).willReturn(memberDto);

        // then
        mockMvc.perform(
                        get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("member", memberDto))
                .andExpect(view().name("user/user-profile"));
    }

    @Test
    @DisplayName("회원 정보 수정 테스트")
    void editMemberProfile() {

    }
}
