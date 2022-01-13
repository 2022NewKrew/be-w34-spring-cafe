package com.kakao.cafe.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class UserAuthenticationProviderTest {

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    UserAuthenticationProvider userAuthenticationProvider;

    private Authentication authentication;

    @BeforeEach
    void setUp() {
        authentication = mock(Authentication.class);
    }

    @Test
    @DisplayName("UserDetailsService 를 활용한 검증과 인증완료된 토큰 발행 -> 비밀번호가 맞지 않음")
    void authenticate_wrongPassword() {
        //Given
        String inputUsername = "gallix@kakao.com";
        String inputPassword = "abcd1234!";
        given(authentication.getName()).willReturn(inputUsername);
        given(authentication.getCredentials()).willReturn(inputPassword);

        String userDetailsPassword = "gallixzzang";
        UserDetails userDetailsByDB = getMockUserDetailsWithPassword(userDetailsPassword);
        given(userDetailsService.loadUserByUsername(inputUsername)).willReturn(userDetailsByDB);

        given(passwordEncoder.matches(inputPassword, userDetailsPassword)).willReturn(false);

        //When, Then
        assertThrows(BadCredentialsException.class, () -> userAuthenticationProvider.authenticate(authentication));
    }

    private UserDetails getMockUserDetailsWithPassword(String password) {
        UserDetails userDetails = mock(UserDetails.class);
        given(userDetails.getPassword()).willReturn(password);
        return userDetails;
    }

    @Test
    @DisplayName("UserDetailsService 를 활용한 검증과 인증완료된 토큰 발행 -> 정상, 인증 완료된 토큰 확인")
    void authenticate_checkAuthentication() {
        //Given
        String inputUsername = "gallix@kakao.com";
        String inputPassword = "abcd1234!";
        given(authentication.getName()).willReturn(inputUsername);
        given(authentication.getCredentials()).willReturn(inputPassword);

        UserDetails userDetailsByDB = getMockUserDetailsWithUsernameAndPassword(inputUsername, inputPassword);
        given(userDetailsService.loadUserByUsername(inputUsername)).willReturn(userDetailsByDB);

        given(passwordEncoder.matches(inputPassword, inputPassword)).willReturn(true);

        //When
        Authentication result = userAuthenticationProvider.authenticate(authentication);

        //Then
        checkAuthenticationWithUserDetails(userDetailsByDB, result);
    }

    private UserDetails getMockUserDetailsWithUsernameAndPassword(String username, String password) {
        UserDetails userDetails = mock(UserDetails.class);
        given(userDetails.getUsername()).willReturn(username);
        given(userDetails.getPassword()).willReturn(password);
        given(userDetails.getAuthorities()).willReturn(Collections.emptyList());
        return userDetails;
    }

    private void checkAuthenticationWithUserDetails(UserDetails userDetails, Authentication result) {
        assertEquals(userDetails.getUsername(), result.getPrincipal());
        assertNull(result.getCredentials());
        assertEquals(userDetails.getAuthorities(), result.getAuthorities());
    }
}