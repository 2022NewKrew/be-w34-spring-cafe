package com.kakao.cafe.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.kakao.cafe.domain.user.Email;
import com.kakao.cafe.domain.user.Name;
import com.kakao.cafe.domain.user.Password;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserName;
import com.kakao.cafe.dto.user.ProfileResponseDto;
import com.kakao.cafe.dto.user.SignupRequestDto;
import com.kakao.cafe.dto.user.UserListResponseDto;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    void signupRequestDtoToUser_Invoked_ReturnsCorrectUser() {
        SignupRequestDto base = new SignupRequestDto("MappingTest", "MappingTest", "MappingTest", "MappingTest@email.com");
        User actual = userMapper.signupRequestDtoToUser(base);

        assertThat(actual.getUserName().getValue()).isEqualTo(base.getUserName());
        assertThat(actual.getPassword().getValue()).isEqualTo(base.getPassword());
        assertThat(actual.getName().getValue()).isEqualTo(base.getName());
        assertThat(actual.getEmail().getValue()).isEqualTo(base.getEmail());
    }

    @Test
    void userToProfileResponseDto_Invoked_ReturnsCorrectDto() {
        User base = new User(UUID.randomUUID(), new UserName("MappingTest"), new Password("MappingTest"), new Name("MappingTest"), new Email("MappingTest@email.com"));
        ProfileResponseDto actual = userMapper.userToProfileResponseDto(base);

        assertThat(actual.getName()).isEqualTo(base.getName().getValue());
        assertThat(actual.getEmail()).isEqualTo(base.getEmail().getValue());
    }

    @Test
    void userListToUserListResponseDtoList_Invoked_ReturnsCorrectDtoList() {
        List<User> base = List.of(
                new User(UUID.randomUUID(), new UserName("MappingTest1"), new Password("MappingTest1"), new Name("MappingTest1"), new Email("MappingTest1@email.com")),
                new User(UUID.randomUUID(), new UserName("MappingTest2"), new Password("MappingTest2"), new Name("MappingTest2"), new Email("MappingTest2@email.com"))
        );
        List<UserListResponseDto> actual = userMapper.userListToUserListResponseDtoList(base);

        assertThat(actual.get(0).getId()).isEqualTo(base.get(0).getId().toString());
        assertThat(actual.get(0).getUserName()).isEqualTo(base.get(0).getUserName().getValue());
        assertThat(actual.get(0).getEmail()).isEqualTo(base.get(0).getEmail().getValue());
        assertThat(actual.get(0).getName()).isEqualTo(base.get(0).getName().getValue());
        assertThat(actual.get(1).getId()).isEqualTo(base.get(1).getId().toString());
        assertThat(actual.get(1).getUserName()).isEqualTo(base.get(1).getUserName().getValue());
        assertThat(actual.get(1).getEmail()).isEqualTo(base.get(1).getEmail().getValue());
        assertThat(actual.get(1).getName()).isEqualTo(base.get(1).getName().getValue());
    }
}
