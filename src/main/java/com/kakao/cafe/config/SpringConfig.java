package com.kakao.cafe.config;

import com.kakao.cafe.repository.user.UserAccountNoDbUseRepository;
import com.kakao.cafe.repository.Repository;
import com.kakao.cafe.service.UserAccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringConfig {
//    private final DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource){
//        this.dataSource = dataSource;
//    }
//
    @Bean
    public UserAccountService UserAccountService(){
        return new UserAccountService(UserRepository(), getPasswordEncoder());
    }
//
//    @Bean
//    public UserAccountRepository UserAccountRepository(){
//        return new UserAccountRepository(dataSource);
//    }

    @Bean
    public Repository UserRepository(){
        return new UserAccountNoDbUseRepository();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
