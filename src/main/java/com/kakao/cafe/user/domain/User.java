package com.kakao.cafe.user.domain;

import com.kakao.cafe.model.NamedEntity;
import org.springframework.stereotype.Controller;

@Controller
public class User extends NamedEntity {

    private String userId;

    private String password;

    private String email;

    private String profileImage;


}
