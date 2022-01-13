package com.kakao.cafe.security;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.error.exception.nonexist.UserNotFoundedException;
import com.kakao.cafe.error.msg.UserErrorMsg;
import com.kakao.cafe.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Test
    @DisplayName("입력된 username(email) 로 유저 검색하여 UserDetails 생성 -> 그런 애 없다고 한다.")
    void loadUserByUsername_nonExistEmail() {
        //Given
        String nonExistEmail = "nonexist@kakao.com";
        given(userService.findByEmail(nonExistEmail)).willThrow(new UserNotFoundedException(UserErrorMsg.USER_NOT_FOUNDED.getDescription()));

        //When, Then
        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername(nonExistEmail));
    }

    @Test
    @DisplayName("입력된 username(email) 로 유저 검색하여 UserDetails 생성 -> UserDetails 확인")
    void loadUserByUsername_checkUserDetails() {
        //Given
        User user = createUser();
        String userEmail = user.getEmail();
        given(userService.findByEmail(userEmail)).willReturn(user);

        //When
        UserDetails result = userDetailsService.loadUserByUsername(userEmail);

        //Then
        checkUserDetailsWithUser(user, result);
    }

    private User createUser() {
       return User.builder()
                .id(Long.valueOf(1))
                .email("gallix@kakao.com")
                .password("abcd1234!")
                .nickName("gallix")
                .build();
    }

    private void checkUserDetailsWithUser(User user, UserDetails userDetails) {
        assertEquals(user.getEmail(), userDetails.getUsername());
        assertEquals(user.getPassword(), userDetails.getPassword());
        checkAuthorityOfUserDetails(userDetails);
    }

    private void checkAuthorityOfUserDetails(UserDetails userDetails) {
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        authorities.stream().anyMatch( grantedAuthority -> grantedAuthority.getAuthority().equals("USER"));
    }
}