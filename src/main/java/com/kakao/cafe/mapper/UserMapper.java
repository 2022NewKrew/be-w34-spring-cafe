package com.kakao.cafe.mapper;

import com.kakao.cafe.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    String insert = "INSERT INTO user(userId, userName, userPw, userEmail) values(#{user.userId}, #{user.userName}, #{user.userPw}, #{user.userEmail})";
    String select = "SELECT * FROM user";
    String selectByKey = "SELECT * FROM user where key = #{key}";

    @Insert(insert)
    @Options(useGeneratedKeys = true, keyProperty = "key")
    int insert(@Param("user") User user);

    @Select(select)
    List<User> findAll();

    @Select(selectByKey)
    User findByKey(Long key);
}
