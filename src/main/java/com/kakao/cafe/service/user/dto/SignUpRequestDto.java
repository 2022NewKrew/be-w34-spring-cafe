<<<<<<< HEAD:src/main/java/com/kakao/cafe/service/user/dto/SignUpRequestDto.java
package com.kakao.cafe.service.user.dto;
=======
package com.kakao.cafe.service.dto;
>>>>>>> 21a0e77 (review 사항 반영):src/main/java/com/kakao/cafe/service/dto/SignUpRequestDto.java

public class SignUpRequestDto {

    private String userId;

    private String password;

    private String name;

    private String email;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "SignUpRequestDto{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
