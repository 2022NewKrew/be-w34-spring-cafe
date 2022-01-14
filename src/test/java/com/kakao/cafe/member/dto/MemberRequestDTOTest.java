package com.kakao.cafe.member.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class MemberRequestDTOTest {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @MethodSource("invalidEmails")
    @DisplayName("이메일은 이메일 주소 형식에 맞아야 한다.")
    void invalidEmailTest(MemberRequestDTO memberRequestDTO) throws Exception {
        mockMvc.perform(post("/members")
                        .flashAttr("memberRequestDTO", memberRequestDTO)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(model().attributeExists("errorMessage"))
                .andExpect(view().name("/error"));
    }

    static Stream<Arguments> invalidEmails() {
        return Stream.of(
                Arguments.of(new MemberRequestDTO(" ", "name", "123", "123")),
                Arguments.of(new MemberRequestDTO("fdf", "name", "123", "123")),
                Arguments.of(new MemberRequestDTO("sdf@", "name", "123", "123")),
                Arguments.of(new MemberRequestDTO("", "name", "123", "123")),
                Arguments.of(new MemberRequestDTO(null, "name", "123", "123"))
        );
    }

    @ParameterizedTest
    @MethodSource("invalidNicknames")
    @DisplayName("닉네임은 공백일 수 없다.")
    void invalidNicknameTest(MemberRequestDTO memberRequestDTO) throws Exception {
        mockMvc.perform(post("/members")
                        .flashAttr("memberRequestDTO", memberRequestDTO)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(model().attributeExists("errorMessage"))
                .andExpect(view().name("/error"));
    }

    static Stream<Arguments> invalidNicknames() {
        return Stream.of(
                Arguments.of(new MemberRequestDTO("flip@kakao.com", "", "123", "123")),
                Arguments.of(new MemberRequestDTO("flip@kakao.com", " ", "123", "123")),
                Arguments.of(new MemberRequestDTO("flip@kakao.com", null, "123", "123"))
        );
    }

    @ParameterizedTest
    @MethodSource("invalidPasswords")
    @DisplayName("패스워드는 빈 문자열일 수 없고, 패드워드 확인과 일치해야 한다.")
    void invalidPasswordTest(MemberRequestDTO memberRequestDTO) throws Exception {
        mockMvc.perform(post("/members")
                        .flashAttr("memberRequestDTO", memberRequestDTO)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(model().attributeExists("errorMessage"))
                .andExpect(view().name("/error"));
    }

    static Stream<Arguments> invalidPasswords() {
        return Stream.of(
                Arguments.of(new MemberRequestDTO("flip@kakao.com", "name", "", "")),
                Arguments.of(new MemberRequestDTO("flip@kakao.com", "name", null, null)),
                Arguments.of(new MemberRequestDTO("flip@kakao.com", "name", "1234", "123"))
        );
    }
}
