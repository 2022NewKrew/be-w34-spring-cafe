package com.kakao.cafe.system;

import com.kakao.cafe.user.User;
import com.kakao.cafe.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by melodist
 * Date: 2022-01-31 031
 * Time: 오후 9:44
 */
@SpringBootTest
public class LoginServiceUnitTest {
    @SpyBean(name = "passwordEncoder")
    private PasswordEncoder passwordEncoder;

    @SpyBean(name = "userRepository")
    private UserRepository userRepository;

    @Autowired
    private LoginService loginService;

    @Test
    public void loginServiceMockingTest(){
        // given
        String userId = "melodist";
        String password = "test";
        BDDMockito.given(userRepository.findUserByUserId(userId))
                .willReturn(new User(1, userId, password, "test", "melodist@test.com"));
        BDDMockito.given(passwordEncoder.matches(password, "test")).willReturn(true);

        // when
        User user = loginService.login(userId, password);

        // then
        assertThat(user.getUserId()).isEqualTo(userId);
    }

    @Test
    public void loginServiceSpyingTest(){
        // given
        String userId = "melodist";
        String password = "test";
        String encodedPassword = passwordEncoder.encode(password);
        BDDMockito.given(userRepository.findUserByUserId(userId))
                .willReturn(new User(1, userId, encodedPassword, "test", "melodist@test.com"));

        // when
        User user = loginService.login(userId, password);

        // then
        assertThat(user.getUserId()).isEqualTo(userId);
    }

}
