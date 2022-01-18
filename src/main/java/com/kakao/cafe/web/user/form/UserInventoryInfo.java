<<<<<<< HEAD:src/main/java/com/kakao/cafe/user/application/port/in/UserInventoryInfo.java
package com.kakao.cafe.user.application.port.in;

public class UserInventoryInfo {
=======
package com.kakao.cafe.web.user.form;

public class UserInventoryInfo {
    private final int index;
>>>>>>> JehPark:src/main/java/com/kakao/cafe/web/user/form/UserInventoryInfo.java
    private final String nickName;
    private final String name;
    private final String email;
    private final Long userId;

<<<<<<< HEAD:src/main/java/com/kakao/cafe/user/application/port/in/UserInventoryInfo.java
    public UserInventoryInfo(long userId, String nickName, String name, String email) {
=======
    public UserInventoryInfo(int index, long userId, String nickName, String name, String email) {
        this.index = index;
>>>>>>> JehPark:src/main/java/com/kakao/cafe/web/user/form/UserInventoryInfo.java
        this.userId = userId;
        this.nickName = nickName;
        this.name = name;
        this.email = email;
    }
}
