package com.kakao.cafe.user.presentation.mapper;

import com.kakao.cafe.user.domain.entity.User;
import com.kakao.cafe.user.domain.entity.UserInfo;
import com.kakao.cafe.user.presentation.dto.JoinRequest;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class JoinRequestToUserConverter implements Converter<JoinRequest, User> {
    @Override
    public User convert(MappingContext<JoinRequest, User> context) {
        JoinRequest joinRequest = context.getSource();
        UserInfo userInfo = new UserInfo(joinRequest.getName(), joinRequest.getEmail());
        return new User(joinRequest.getUserId(), joinRequest.getPassword(), userInfo);
    }
}
