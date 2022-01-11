package com.kakao.cafe.mapper;

import com.kakao.cafe.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    final String insert = "INSERT INTO user(userId, userName, userPw, userEmail) values(#{user.userId}, #{user.userName}, #{user.userPw}, #{user.userEmail})";
    final String select = "SELECT * FROM user";
    final String selectById = "SELECT * FROM user where id = #{id}";

    @Insert(insert)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(@Param("user") User user);

    @Select(select)
    List<User> findAll();

    @Select(selectById)
    User findById(Long id);
}
