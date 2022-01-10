package com.kakao.cafe.system;

import com.kakao.cafe.user.MemoryUserRepository;
import com.kakao.cafe.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by melodist
 * Date: 2022-01-10 010
 * Time: 오후 1:55
 */
@Configuration
public class RepoConfiguration {

    @Bean
    public UserRepository userRepository() {
        return new MemoryUserRepository();
    }
}
