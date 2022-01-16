package com.kakao.cafe.user.application;

import com.kakao.cafe.user.application.port.in.FindUserDto;
import com.kakao.cafe.user.application.port.in.FindUserQuery;
import com.kakao.cafe.user.application.port.out.LoadUserPort;
import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.domain.UserId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FindUserService implements FindUserQuery {

    private final LoadUserPort loadUserPort;

    public FindUserService(LoadUserPort loadUserPort) {
        this.loadUserPort = loadUserPort;
    }

    @Override
    public FindUserDto find(UserId userId) {
        Optional<User> loadedUser = this.loadUserPort.load(userId);
        return loadedUser.map(user -> new FindUserDto(
                user.getUserId(),
                user.getEmail(),
                user.getNickname()
            ))
            .orElseThrow(NullPointerException::new);
    }

    @Override
    public List<FindUserDto> findAll() {
        return this.loadUserPort.loadAll().stream()
            .map(user -> new FindUserDto(
                user.getUserId(),
                user.getEmail(),
                user.getNickname()
            ))
            .collect(Collectors.toUnmodifiableList());
    }
}
