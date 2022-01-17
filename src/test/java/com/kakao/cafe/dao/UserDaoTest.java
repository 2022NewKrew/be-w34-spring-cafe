package com.kakao.cafe.dao;

import com.kakao.cafe.config.SpringJdbcConfig;
import com.kakao.cafe.dao.mapper.UserRowMapper;
import com.kakao.cafe.dto.user.UserDto;
import com.kakao.cafe.vo.UserVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class UserDaoTest {

    DataSource dataSource;
    SpringJdbcConfig springJdbcConfig;
    UserDao userDao;
    ModelMapper modelMapper;

    @BeforeEach
    void init() throws SQLException {
        springJdbcConfig = new SpringJdbcConfig();
        modelMapper = springJdbcConfig.modelMapper();
        dataSource = springJdbcConfig.dataSource();
        dataSource.getConnection();
        userDao = new UserDao(new JdbcTemplate(dataSource),new UserRowMapper());
    }

    @Test
    @DisplayName("회원 저장 및 회원 조회 성공")
    void findByUserId() {
        UserDto userDto = new UserDto();
        userDto.setUserId("test");
        userDto.setPassword("1234");
        userDto.setName("김대환");
        userDto.setEmail("david.dh@kakaocorp.com");

        userDao.save(modelMapper.map(userDto, UserVo.class));

        UserVo matchedUser = userDao.findByUserId(userDto.getUserId());
        assertThat(modelMapper.map(matchedUser,UserDto.class).toString()).isEqualTo(userDto.toString());

    }
}
