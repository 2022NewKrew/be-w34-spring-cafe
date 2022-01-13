package com.kakao.cafe.dao;

import com.kakao.cafe.helper.CollectionHelper;
import com.kakao.cafe.model.User;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CafeUserDaoImpl implements CafeUserDao {

    DataSource dataSource;

    public CafeUserDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void signUp(User newUser) {
//        String userId = newUser.getUserId();
//        if( userId == null || userMap.containsKey(userId) ) {
//            return;
//        }
//        userMap.put(userId, newUser);
    }

    @Override
    public List<User> getUserList() {
//        List<User> userList = CollectionHelper.convertMapToList(userMap);
//        userList.forEach(user -> user.setPassword(null));
        List<User> userList = null;
        return userList;
    }

    @Override
    public User getUserProfile(String userId) {
        User user = null;
//        if( userId != null ) {
//            user = userMap.getOrDefault(userId, null);
//        }
//        if( user != null) {
//            user.setPassword(null);
//        }
        return user;
    }
}
