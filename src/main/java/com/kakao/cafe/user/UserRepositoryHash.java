package com.kakao.cafe.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UserRepository의 구현체입니다.
 * Database를 사용하면 안되어 HashMap으로 데이터베이스를 구성합니다.
 *
 * @author jm.hong
 * @deprecated H2 DataBase 버전이 추가되어 향후 삭제됩니다.
 */
public class UserRepositoryHash implements UserRepository {

    private static Map<Long, User> inMemoryDatabase = new HashMap<>();
    private static long autoIncrementNumber = 0L;

    // 임시 데이터입니다.
    static {
        User user1 = new User();
        user1.setName("Hongjeongmin");
        user1.setId(autoIncrementNumber);
        user1.setUserId("jm.hong");
        user1.setPassword("jm.hong");
        user1.setEmail("jm.hong@kakaocorp.com");
        inMemoryDatabase.put(autoIncrementNumber++, user1);

        User user2 = new User();
        user2.setName("Kimgisun");
        user2.setId(autoIncrementNumber);
        user2.setUserId("kim.gi");
        user2.setPassword("kim.gi");
        user2.setEmail("kim.gi@kakaocorp.com");
        inMemoryDatabase.put(autoIncrementNumber++, user2);
    }


    @Override
    public Long save(User user) {

        user.setId(autoIncrementNumber);
        inMemoryDatabase.put(autoIncrementNumber++, user);
        return autoIncrementNumber;
    }

    @Override
    public User findOne(Long id) {
        return inMemoryDatabase.get(id);
    }

    @Override
    public List<User> findAll() {

        // TODO stream 으로 변경
        List<User> users = new ArrayList<>();
        List<Long> keys = new ArrayList<>(inMemoryDatabase.keySet());

        for (Long key : keys) {
            users.add(inMemoryDatabase.get(key));
        }

        return users;
    }

    @Override
    public boolean update(User user) {

        User origin = inMemoryDatabase.get(user.getId());

        if (origin == null) {
            return false;
        }

        origin.setUserId(user.getUserId());
        origin.setName(user.getName());
        origin.setEmail(user.getEmail());

        inMemoryDatabase.put(user.getId(), origin);

        return true;
    }
}
