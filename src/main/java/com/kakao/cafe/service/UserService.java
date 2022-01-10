package com.kakao.cafe.service;

import com.kakao.cafe.model.User;

import java.util.*;

public interface UserService {

    User filterUserById(List<User> users, String userId);

}
