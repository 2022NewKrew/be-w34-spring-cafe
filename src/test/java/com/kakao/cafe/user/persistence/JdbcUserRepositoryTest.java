package com.kakao.cafe.user.persistence;

import com.kakao.cafe.config.TestConfig;
import com.kakao.cafe.user.data.UsersData;
import com.kakao.cafe.user.domain.entity.User;
import com.kakao.cafe.user.domain.entity.UserInfo;
import com.kakao.cafe.user.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@JdbcTest
@Import(TestConfig.class)
class JdbcUserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp(){

    }

    @Test
    @DisplayName("Repository 생성 성공")
    void successCreateRepository(){
        assertThat(userRepository).isNotNull();
    }

    @ParameterizedTest
    @DisplayName("사용자 가져오기 성공")
    @MethodSource("com.kakao.cafe.user.data.UsersData#getUsers")
    void successGetUser(User user){
        //given
        UsersData.getUserList().forEach(userRepository::save);

        //when
        User savedUser = userRepository.getUser(user.getUserId()).orElseThrow();

        //then
        assertThat(savedUser).isEqualTo(user);
    }

    @Test
    @DisplayName("사용자 전부 가져오기 성공")
    void successGetAllUsers(){
        //given
        UsersData.getUserList().forEach(userRepository::save);

        //when
        List<User> savedUsers = userRepository.getAllUsers();

        //then
        assertThat(savedUsers).isEqualTo(UsersData.getUserList());
    }

    @ParameterizedTest
    @DisplayName("사용자 저장 성공")
    @MethodSource("com.kakao.cafe.user.data.UsersData#getUsers")
    void successSaveUser(User user){
        //given

        //when
        userRepository.save(user);
        User savedUser = userRepository.getUser(user.getUserId()).orElseThrow();

        //then
        assertThat(savedUser).isEqualTo(user);
    }
    @ParameterizedTest
    @DisplayName("사용자 이미 존재할 때 저장 실패")
    @MethodSource("com.kakao.cafe.user.data.UsersData#getUsers")
    void failedSaveWhenExistUser(User user){
        //given
        userRepository.save(user);

        //when
        //then
        assertThatThrownBy(() -> userRepository.save(user))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("이미");
    }


    @ParameterizedTest
    @DisplayName("사용자 정보 갱신 성공")
    @MethodSource("com.kakao.cafe.user.data.UsersData#getUsers")
    void successUpdateUserInfo(User user){
        //given
        userRepository.save(user);
        UserInfo newUserInfo = new UserInfo("2".concat(user.getUserInfo().getName()), "2".concat(user.getUserInfo().getEmail()));

        //when
        userRepository.update(user.getUserId(), newUserInfo);
        User updatedUser = userRepository.getUser(user.getUserId()).orElseThrow();

        //then
        assertThat(updatedUser.getUserInfo()).isEqualTo(newUserInfo);
    }

    @ParameterizedTest
    @DisplayName("사용자가 없어서 갱신 실패")
    @MethodSource("com.kakao.cafe.user.data.UsersData#getUsers")
    void failedUpdateUserInfoWhenNotExist(User user){
        //given
        UserInfo newUserInfo = new UserInfo("2".concat(user.getUserInfo().getName()), "2".concat(user.getUserInfo().getEmail()));

        //when
        //then
        assertThatThrownBy(() -> userRepository.update(user.getUserId(), newUserInfo))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("없어");
    }
}