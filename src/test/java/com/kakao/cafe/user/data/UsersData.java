package com.kakao.cafe.user.data;

import com.kakao.cafe.user.domain.entity.User;
import com.kakao.cafe.user.domain.entity.UserInfo;
import com.kakao.cafe.user.presentation.dto.JoinRequest;
import com.kakao.cafe.user.presentation.dto.UpdateUserInfoRequest;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

public class UsersData {
    public static Stream<Arguments> getUpdateRequests(){
        return getUserList().stream()
                .map(user -> Arguments.of(user.getUserId(),
                        new UpdateUserInfoRequest(user.getUserInfo().getName(), user.getUserInfo().getEmail()))
                );
    }

    public static Stream<Arguments> getJoinRequests(){
        return getUserList().stream()
                .map(user -> new JoinRequest(user.getUserId(), user.getPassword(), user.getUserInfo().getName(), user.getUserInfo().getEmail()))
                .map(Arguments::of);
    }


    public static Stream<Arguments> getWrongConstructParameters(){
        return Stream.of(
                Arguments.of(null, "asdf1234", "name", "email@daum.net"),
                Arguments.of("us", "asdf1234", "name", "email@daum.net"),
                Arguments.of("userId", "asdf123", "name", "email@daum.net"),
                Arguments.of("userId", "asdf1234", "nameName", "email@daum.net"),
                Arguments.of("userId", "asdf1234", "name", "emaildaum.net")
        );
    }

    public static Stream<Arguments> getUsers(){
        return getUserList().stream().map(Arguments::of);
    }

    public static List<User> getUserList(){
        return List.of(
                new User("asdf", "asdf1234", new UserInfo("asdf", "asdf@naver.com")),
                new User("hello", "hello1234", new UserInfo("hello", "hello@naver.com")),
                new User("world", "world1234", new UserInfo("world", "world@naver.com")),
                new User("good", "good1234", new UserInfo("good", "good@naver.com"))
        );
    }
}
