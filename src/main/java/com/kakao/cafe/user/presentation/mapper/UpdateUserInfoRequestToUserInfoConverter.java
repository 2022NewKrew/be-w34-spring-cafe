package com.kakao.cafe.user.presentation.mapper;

import com.kakao.cafe.user.domain.entity.UserInfo;
import com.kakao.cafe.user.presentation.dto.UpdateUserInfoRequest;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserInfoRequestToUserInfoConverter implements Converter<UpdateUserInfoRequest, UserInfo> {
    @Override
    public UserInfo convert(MappingContext<UpdateUserInfoRequest, UserInfo> context) {
        UpdateUserInfoRequest request = context.getSource();
        return new UserInfo(request.getName(), request.getEmail());
    }
}
