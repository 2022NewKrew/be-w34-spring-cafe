package com.kakao.cafe.mapper;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class ArticleMapperTest {
    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    @Test
    @DisplayName("UserMapper 가 UserDto를 Entㅑty로 매핑한다.")
    void toEntity() {
        UserDto userDto = new UserDto();
        userDto.setUserId("trevi");
        userDto.setEmail("trevi.kim@abc.com");
        userDto.setName("김현석");
        userDto.setPassword("qweqwe");

        User user = User.builder().
                userId("trevi").
                name("김현석").
                email("trevi.kim@abc.com").
                password("qweqwe").
                build();

        Assertions.assertThat(user).usingRecursiveComparison().isEqualTo(userMapper.toEntity(userDto));
    }
}