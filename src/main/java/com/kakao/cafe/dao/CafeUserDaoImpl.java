package com.kakao.cafe.dao;

import com.kakao.cafe.helper.CollectionHelper;
import com.kakao.cafe.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CafeUserDaoImpl implements CafeUserDao {

    Map<String, User> userMap = new HashMap<>();

    @Override
    public void signIn(User newUser) {
        String userId = newUser.getUserId();
        if( userId == null || userMap.containsKey(userId) ) {
            return;
        }
        userMap.put(userId, newUser);
    }

    @Override
    public List<User> getUserList() {
        return CollectionHelper.convertMapToList(userMap);
    }

    @Override
    public User getUserProfile(String userId) {
        User user = null;
        if( userId != null ) {
            userMap.getOrDefault(userId, null);
        }
        return user;
    }
}
