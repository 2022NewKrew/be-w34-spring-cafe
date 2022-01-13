package com.kakao.cafe.controller;

import com.kakao.cafe.entity.User;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UserIndexController {

    public void sort(List<User> userList) {
        Collections.sort(userList, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getUserId().compareTo(o2.getUserId());
            }
        });
    }

    public void indexing(List<User> userList) {
        sort(userList);
        for(int i=0; i<userList.size(); i++) {
            userList.get(i).setIndex(i+1);
        }
    }
}
