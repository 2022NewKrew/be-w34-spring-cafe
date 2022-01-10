package com.kakao.cafe.repository;

import com.kakao.cafe.entity.User;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private final List<User> data = new ArrayList<>();
    // TODO 테스트 데이터 삭제
    {
        data.add(
                new User.Builder()
                        .id("test")
                        .email("test@example.com")
                        .password("1234")
                        .name("test")
                        .build()
        );
    }

    @Nullable
    public User create(User user) {
        // NOTE 중복 확인을 Repository에서 해야 할 지, Service에서 해야 할지?
        User existing = find(user.getId()).orElse(null);
        if (existing != null) {
            // NOTE Exception을 던지는 것이 더 나을 것 같다. 옳은 접근인가?
            return null;
        }
        data.add(user);
        return user;
    }

    public List<User> list() {
        return Collections.unmodifiableList(data);
    }

    @Nullable
    public User get(String id) {
        return find(id).orElse(null);
    }

    @Nullable
    public User login(String id, String password) {
        Optional<User> found = find(id);
        if (found.isEmpty()) {
            return null;
        }
        User user = found.get();
        boolean ok = user.checkPassword(password);
        if (!ok) {
            return null;
        }
        return user;
    }

    private Optional<User> find(String id) {
        return data.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }
}
