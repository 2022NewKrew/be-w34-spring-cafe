package com.kakao.cafe.mapper;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserFormDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    String insert = "INSERT INTO user(id, name, pw, email) values(#{user.id}, #{user.name}, #{user.pw}, #{user.email})";
    String selectAll = "SELECT * FROM user";
    String selectByKey = "SELECT * FROM user where key = #{key}";
    String updateByKey = "UPDATE user SET id = #{user.id}, name = #{user.name}, pw = #{user.pw}, email = #{user.email} WHERE key = #{key}";

    @Select(selectAll)
    List<User> selectAll();

    @Select(selectByKey)
    User selectByKey(Long key);

    @Insert(insert)
    @Options(useGeneratedKeys = true, keyProperty = "key")
    long insert(@Param("user") User user);

    @Update(updateByKey)
    void update(long key, @Param("user") UserFormDTO user);
}