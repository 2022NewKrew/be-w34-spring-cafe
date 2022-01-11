package com.kakao.cafe.model;

import com.kakao.cafe.model.data_storage.AccountTable;

import java.security.NoSuchAlgorithmException;

/**
 * author    : brody.moon
 * version   : 1.0
 * User 계정 정보 클래스입니다.
 */
public class UserAccount {
    private final String userId;
    private final String password;
    private final String name;
    private final String email;

    /**
     * 중복 등록을 방지하기 위해 private 생성자로 선언하였습니다.
     * @param userAccountDTO UserAccountDTO 객체
     */
    private UserAccount(UserAccountDTO userAccountDTO) {
        this.userId = userAccountDTO.getUserId();
        this.password = userAccountDTO.getPassword();
        this.name = userAccountDTO.getName();
        this.email = userAccountDTO.getEmail();
    }

    /**
     * 계정 생성을 위한 static 메서드입니다.
     * @param userAccountDTO    UserAccountDTO 객체
     * @return                  UserAccount 객체
     */
    public static UserAccount createUserAccount(boolean isUpdateProcess, UserAccountDTO userAccountDTO){
        if(isUpdateProcess || !AccountTable.contains(userAccountDTO.getUserId()))
            return new UserAccount(userAccountDTO);

        return null;
    }

    /**
     * password 일치 여부 확인하는 메서드입니다.
     * @param password  사용자 요청 password
     * @return          password 가 일치하는지 여부
     */
    public boolean isVaildPassword(String password){
        if(this.password.equals(password))
            return true;
        return false;
    }

    /**
     * UserAccount 클래스를 DTO 클래스로 매핑해주는 메서드입니다.
     * password 는 암호화 해서 저장합니다.
     * @return  DTO 클래스
     */
    public UserAccountDTO toUserAccountDTO(){
        SHA256 sha256 = new SHA256();
        UserAccountDTO userAccountDTO = null;

        try {
            userAccountDTO = new UserAccountDTO(userId, sha256.encrypt(password), name, email);
        } catch (NoSuchAlgorithmException e) {

        }

        return userAccountDTO;
    }
}
