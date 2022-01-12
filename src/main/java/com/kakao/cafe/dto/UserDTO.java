package com.kakao.cafe.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDTO {
    private long key;
    private String id;
    private String name;
    private String pw;
    private String email;
}
