package com.kakao.cafe.user.data;

import com.kakao.cafe.user.domain.entity.User;
import com.kakao.cafe.user.domain.entity.UserInfo;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

public class UsersData {
    public static Stream<Arguments> getUserStream(){
        return getUsers().stream().map(Arguments::of);
    }

    public static List<User> getUsers(){
        return List.of(
                new User("asdf", "asdf1234", new UserInfo("asdf", "asdf@naver.com")),
                new User("hello", "hello1234", new UserInfo("hello", "hello@naver.com")),
                new User("world", "world1234", new UserInfo("world", "world@naver.com")),
                new User("good", "good1234", new UserInfo("good", "good@naver.com"))
        );
    }
}
