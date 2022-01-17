package com.kakao.cafe.rowmapper;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.user.ProfileDto;
import com.kakao.cafe.dto.user.SimpleUserInfo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserRowMapper {

    public RowMapper<User> getUserRowMapper() {
        return (resultSet, rowNum) ->
                User.builder()
                        .id(resultSet.getLong("id"))
                        .email(resultSet.getString("email"))
                        .nickName(resultSet.getString("nick_name"))
                        .password(resultSet.getString("password"))
                        .createdAt(resultSet.getTimestamp("created_at").toLocalDateTime())
                        .build();
    }

    public RowMapper<SimpleUserInfo> getSimpleUserInfoMapper() {
        return (resultSet, rowNum) -> {
            LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
            String joinDate = String.format("%d-%d-%d", createdAt.getYear(), createdAt.getMonth().getValue(), createdAt.getDayOfMonth());
            return SimpleUserInfo.builder()
                    .email(resultSet.getString("email"))
                    .nickName(resultSet.getString("nick_name"))
                    .joinDate(joinDate)
                    .build();
        };
    }

    public RowMapper<ProfileDto> getProfileDtoMapper() {
        return (resultSet, rowNum) ->
                ProfileDto.builder()
                        .id(resultSet.getLong("id"))
                        .nickName(resultSet.getString("nick_name"))
                        .password(resultSet.getString("password"))
                        .build();
    }
}
