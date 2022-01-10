package com.kakao.cafe.mapper;

import com.kakao.cafe.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    final String insert = "INSERT INTO user(user_id, user_name, user_pw, user_email) values(#{user.user_id}, #{user.user_name}, #{user.user_pw}, #{user.user_email})";
    final String select = "SELECT * FROM user";
    @Insert(insert)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(@Param("user") User user);

    @Select(select)
    List<User> findAll();
}
