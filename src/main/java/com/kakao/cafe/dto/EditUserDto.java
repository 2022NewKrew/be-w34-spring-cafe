package com.kakao.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EditUserDto {
    private String email;
    private String username;
    private String password;
    private String newPassword;
    private String newPasswordConfirm;
    private LocalDateTime regDate, modDate;

    public boolean confirmPassword() {
        return newPassword.equals(newPasswordConfirm);
    }
}
