package com.kakao.cafe.application.user;

import com.kakao.cafe.adapter.out.infra.persistence.user.JdbcUserInfoRepository;
import com.kakao.cafe.adapter.out.infra.persistence.user.UserAdapter;
import com.kakao.cafe.adapter.out.infra.persistence.user.UserInfoRepository;
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
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MvcUserConfig {

    public final DataSource dataSource;

    public MvcUserConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public UserInfoRepository userInfoRepository() {
        return new JdbcUserInfoRepository(dataSource);
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
