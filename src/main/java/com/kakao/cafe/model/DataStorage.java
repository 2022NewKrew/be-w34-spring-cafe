package com.kakao.cafe.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataStorage {
    public static final Map<String, UserAccount> DB = new HashMap<>();

    public static boolean isExistUserAccount(String userID){
        return DB.containsKey(userID);
    }

    public static UserAccount lookUpUserInfo(String userID){
        return DB.get(userID);
    }

    public static void saveUserInto(String userId, UserAccount userAccount){
        DB.put(userId, userAccount);
    }

    public static List<UserAccount> allUserAccountInfo(){
        List<UserAccount> userAccountList = new ArrayList<>();

        for(Map.Entry<String, UserAccount> entry: DB.entrySet()){
            userAccountList.add((entry.getValue()));
        }

        return userAccountList;
    }
}
