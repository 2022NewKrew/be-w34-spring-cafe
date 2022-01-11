package com.kakao.cafe.model.dto;

import com.kakao.cafe.model.domain.User;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String userId;
    private String password;
    private String name;
    private String email;
}
