package com.kakao.cafe.user.application;

import com.kakao.cafe.user.application.port.in.CreateUserDto;
import com.kakao.cafe.user.application.port.in.SignUpUseCase;
import com.kakao.cafe.user.application.port.out.SaveUserPort;
import com.kakao.cafe.user.domain.Email;
import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.domain.UserId;

public class SignUpService implements SignUpUseCase {

    private final SaveUserPort saveUserPort;

    public SignUpService(SaveUserPort saveUserPort) {
        this.saveUserPort = saveUserPort;
    }

    @Override
    public UserId signUp(CreateUserDto createUserDto) {
        UserId userId = UserId.create();
        Email email = new Email(createUserDto.getEmail());
        User createdUser = new User(
            userId,
            email,
            createUserDto.getNickname(),
            createUserDto.getPassword());

        saveUserPort.save(createdUser);
        return userId;
    }
}
