package com.kakao.cafe.mapper;

import com.kakao.cafe.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    String insert = "INSERT INTO user(userId, userName, userPw, userEmail) values(#{user.userId}, #{user.userName}, #{user.userPw}, #{user.userEmail})";
    String selectAll = "SELECT * FROM user";
    String selectByKey = "SELECT * FROM user where key = #{key}";

    @Insert(insert)
    @Options(useGeneratedKeys = true, keyProperty = "key")
    long insert(@Param("user") User user);


    @Select(selectAll)
    List<User> selectAll();

    @Select(selectByKey)
    User selectByKey(Long key);
}
