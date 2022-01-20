package com.kakao.cafe.user.application;

import com.kakao.cafe.user.application.port.in.FindUserUseCase;
import com.kakao.cafe.user.application.port.in.FoundUserDto;
import com.kakao.cafe.user.application.port.out.LoadUserPort;
import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.domain.UserId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FindUserService implements FindUserUseCase {

    private final LoadUserPort loadUserPort;

    public FindUserService(LoadUserPort loadUserPort) {
        this.loadUserPort = loadUserPort;
    }

    @Override
    public FoundUserDto find(UserId userId) {
        Optional<User> loadedUser = this.loadUserPort.loadById(userId);
        return loadedUser.map(user -> new FoundUserDto(
                user.getUserId(),
                user.getEmail(),
                user.getNickname()
            ))
            .orElseThrow(NullPointerException::new);
    }

    @Override
    public List<FoundUserDto> findAll() {
        return this.loadUserPort.loadAll().stream()
            .map(user -> new FoundUserDto(
                user.getUserId(),
                user.getEmail(),
                user.getNickname()
            ))
            .collect(Collectors.toUnmodifiableList());
    }
}
