package com.kakao.cafe.adapter.out.infra.persistence.user;

import static com.kakao.cafe.adapter.out.infra.persistence.user.JdbcUserInfoRepository.COLUMN_EMAIL;
import static com.kakao.cafe.adapter.out.infra.persistence.user.JdbcUserInfoRepository.COLUMN_ID;
import static com.kakao.cafe.adapter.out.infra.persistence.user.JdbcUserInfoRepository.COLUMN_NAME;
import static com.kakao.cafe.adapter.out.infra.persistence.user.JdbcUserInfoRepository.COLUMN_PASSWORD;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.exceptions.IllegalEmailException;
import com.kakao.cafe.domain.user.exceptions.IllegalPasswordException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserIdException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserNameException;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            return new User.Builder().userId(rs.getString(COLUMN_ID))
                                     .password(rs.getString(COLUMN_PASSWORD))
                                     .name(rs.getString(COLUMN_NAME))
                                     .email(rs.getString(COLUMN_EMAIL))
                                     .build();
        } catch (IllegalUserIdException e) {
            throw new SQLException("DB에 저장된 userID가 잘못되었습니다.");
        } catch (IllegalPasswordException e) {
            throw new SQLException("DB에 저장된 password가 잘못되었습니다.");
        } catch (IllegalUserNameException e) {
            throw new SQLException("DB에 저장된 userName이 잘못되었습니다.");
        } catch (IllegalEmailException e) {
            throw new SQLException("DB에 저장된 email이 잘못되었습니다.");
        }
    }
}
