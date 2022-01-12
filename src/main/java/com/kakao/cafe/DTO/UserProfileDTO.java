package com.kakao.cafe.DTO;

public class UserProfileDTO {
    private final String name;
    private final String email;
    private final String pictureAddress;

    public UserProfileDTO(String name, String email, String pictureAddress) {
        this.name = name;
        this.email = email;
        this.pictureAddress = pictureAddress;
    }

    public String getPictureAddress() {
        return pictureAddress;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
