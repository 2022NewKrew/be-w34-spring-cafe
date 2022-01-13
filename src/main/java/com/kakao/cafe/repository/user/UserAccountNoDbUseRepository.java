package com.kakao.cafe.repository.user;

import com.kakao.cafe.domain.UserAccount;
import com.kakao.cafe.domain.UserAccountDTO;
import com.kakao.cafe.repository.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * author    : brody.moon
 * version   : 1.0
 * DB 대신 임시로 사용하는 저장소입니다.
 *
 */
public class UserAccountNoDbUseRepository implements Repository<UserAccount, UserAccountDTO, String> {
    private static final Map<String, UserAccount> DB = new HashMap<>();
    private int id = 0;

    @Override
    public UserAccount save(UserAccountDTO userAccountDTO) {
        userAccountDTO.setId(id++);

        UserAccount userAccount = new UserAccount(userAccountDTO);
        DB.put(userAccount.getUserId(), userAccount);
        return userAccount;
    }

    @Override
    public Optional<UserAccount> findById(String userId) {
        return Optional.ofNullable(DB.get(userId));
    }

    @Override
    public List<UserAccount> findAll() {
        return new ArrayList<>(DB.values());
    }

    public void clearStore() {
        DB.clear();
    }
}
