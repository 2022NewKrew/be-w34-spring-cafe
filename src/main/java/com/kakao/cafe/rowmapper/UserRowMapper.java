package com.kakao.cafe.rowmapper;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.user.ProfileDto;
import com.kakao.cafe.dto.user.SimpleUserInfo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Component
public class UserRowMapper {

    public RowMapper<User> getUserRowMapper() {
        return (resultSet, rowNum) -> {
            LocalDateTime createdAtLocal = resultSet.getTimestamp("created_at").toLocalDateTime();
            OffsetDateTime createdAt = createdAtLocal.atOffset(ZoneOffset.of(ZoneOffset.systemDefault().getId()));
            return User.builder()
                    .id(resultSet.getLong("id"))
                    .email(resultSet.getString("email"))
                    .nickName(resultSet.getString("nick_name"))
                    .password(resultSet.getString("password"))
                    .createdAt(resultSet.getTimestamp("created_at").toInstant().atOffset(ZoneOffset.of(ZoneOffset.systemDefault().getId())))
                    .build();
        };
    }

    public RowMapper<SimpleUserInfo> getSimpleUserInfoMapper() {
        return (resultSet, rowNum) -> {
            LocalDateTime createdAtLocal = resultSet.getTimestamp("created_at").toLocalDateTime();
            OffsetDateTime createdAt = createdAtLocal.atOffset(ZoneOffset.of(ZoneOffset.systemDefault().getId()));
            String joinDate = createdAt.format(DateTimeFormatter.ISO_LOCAL_DATE);
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
