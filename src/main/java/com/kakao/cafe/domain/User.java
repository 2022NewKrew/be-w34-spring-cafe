package com.kakao.cafe.domain;

import com.kakao.cafe.dto.UserCreationDTO;
import com.kakao.cafe.util.Encrypt;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
public class User {
    @Setter
    private long id;
    private String userEmail;
    private String userId;
    private String password;
    private LocalDate registerDate;

    @Builder
    public User(Encrypt encrypt, UserCreationDTO dto) {
        this.userEmail = dto.getUserEmail();
        this.userId = dto.getUserId();
        this.password = encrypt.encrypt(dto.getPassword());
        registerDate = LocalDate.now();
    }
}
