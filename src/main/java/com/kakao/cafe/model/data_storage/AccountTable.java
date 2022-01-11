package com.kakao.cafe.model.data_storage;

import com.kakao.cafe.model.UserAccount;
import com.kakao.cafe.model.UserAccountDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author    : brody.moon
 * version   : 1.0
 * DB 대신 임시로 사용하는 저장소입니다.
 *
 */
public class AccountTable {
    private static final Map<String, UserAccount> DB = new HashMap<>();

    /**
     * User 정보가 존재하는지 판단합니다.
     * @param userId    userId
     * @return          userId 가 DB에 존재하는지 여부
     */
    public static boolean contains(String userId){
        return DB.containsKey(userId);
    }

    /**
     * UserID 를 이용해 user 정보를 받아오는 메서드입니다.
     * @param userId    UserId
     * @return          User 정보
     */
    public static UserAccount lookUpUserInfo(String userId){
        return DB.get(userId);
    }

    /**
     * 유저 정보를 저장하는 메서드입니다.
     * @param userId        UserID
     * @param userAccount   계정 정보
     */
    public static void saveUserInto(String userId, UserAccount userAccount){
        DB.put(userId, userAccount);
    }

    /**
     * 모든 등록 계정 정보를 반환하는 메서드입니다.
     * @return  모든 계정 정보 리스트     */
    public static List<UserAccountDTO> allUserAccountInfo(){
        List<UserAccountDTO> userAccountList = new ArrayList<>();

        int i = 1;
        for(Map.Entry<String, UserAccount> entry: DB.entrySet()){
            UserAccountDTO userAccountDTO = entry.getValue().toUserAccountDTO();
            userAccountDTO.setIndex(i++);
            userAccountList.add(userAccountDTO);
        }

        return userAccountList;
    }
}
