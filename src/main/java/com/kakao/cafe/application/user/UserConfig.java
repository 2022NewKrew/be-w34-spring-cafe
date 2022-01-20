package com.kakao.cafe.application.user;

import com.kakao.cafe.adapter.out.infra.persistence.user.JdbcUserInfoRepository;
import com.kakao.cafe.adapter.out.infra.persistence.user.UserAdapter;
import com.kakao.cafe.adapter.out.infra.persistence.user.UserInfoRepository;
import com.kakao.cafe.adapter.out.infra.persistence.user.UserMapper;
import com.kakao.cafe.application.user.port.in.GetUserInfoUseCase;
import com.kakao.cafe.application.user.port.in.LoginUserUseCase;
import com.kakao.cafe.application.user.port.in.SignUpUserUseCase;
import com.kakao.cafe.application.user.port.in.UpdateUserInfoUseCase;
import com.kakao.cafe.application.user.port.out.GetUserEntityPort;
import com.kakao.cafe.application.user.port.out.LoginUserPort;
import com.kakao.cafe.application.user.port.out.RegisterUserPort;
import com.kakao.cafe.application.user.port.out.UpdateUserInfoPort;
import com.kakao.cafe.application.user.service.GetUserInfoService;
import com.kakao.cafe.application.user.service.LoginUserService;
import com.kakao.cafe.application.user.service.SignUpUserService;
import com.kakao.cafe.application.user.service.UpdateUserInfoService;
import com.kakao.cafe.domain.user.User;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

@Configuration
public class UserConfig {

    public final DataSource dataSource;

    @Autowired
    public UserConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public RowMapper<User> userMapper() {
        return new UserMapper();
    }

    @Bean
    public UserInfoRepository userInfoRepository() {
        return new JdbcUserInfoRepository(dataSource, userMapper());
    }

    @Bean
    public LoginUserPort loginUserPort() {
        return new UserAdapter(userInfoRepository());
    }

    @Bean
    public GetUserEntityPort getUserEntityPort() {
        return new UserAdapter(userInfoRepository());
    }

    @Bean
    public RegisterUserPort registerUserPort() {
        return new UserAdapter(userInfoRepository());
    }

    @Bean
    public UpdateUserInfoPort updateUserInfoPort() {
        return new UserAdapter(userInfoRepository());
    }

    @Bean
    public LoginUserUseCase loginUserUseCase() {
        return new LoginUserService(loginUserPort());
    }

    @Bean
    public GetUserInfoUseCase getUserInfoUseCase() {
        return new GetUserInfoService(getUserEntityPort());
    }

    @Bean
    public SignUpUserUseCase signUpUserUseCase() {
        return new SignUpUserService(registerUserPort());
    }

    @Bean
    public UpdateUserInfoUseCase updateUserInfoUseCase() {
        return new UpdateUserInfoService(updateUserInfoPort(), getUserEntityPort());
    }
}
