package com.kakao.cafe.mapper;

import com.kakao.cafe.dto.response.UserResDto;
import com.kakao.cafe.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public List<UserResDto> toUserResDtoList(List<UserEntity> userEntityList) {
        return userEntityList.stream()
                .map(this::toUserResDto)
                .collect(Collectors.toList());
    }

    public UserResDto toUserResDto(UserEntity userEntity) {
        return new UserResDto(userEntity.getId(), userEntity.getUserId(), userEntity.getName(), userEntity.getEmail());
    }
}
