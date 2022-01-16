package com.kakao.cafe.user.adapter.out;

import com.kakao.cafe.user.application.port.out.LoadUserPort;
import com.kakao.cafe.user.application.port.out.SaveUserPort;
import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.domain.UserId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryUserRepository implements SaveUserPort, LoadUserPort {

    private static final Map<UUID, User> store = new HashMap<>();

    @Override
    public void save(User user) {
        UUID uuid = user.getUserId().getUUID();
        store.put(uuid, user);
    }

    @Override
    public Optional<User> load(UserId userId) {
        return Optional.ofNullable(store.get(userId.getUUID()));
    }

    @Override
    public List<User> loadAll() {
        return store.values().stream().collect(Collectors.toUnmodifiableList());
    }

}
