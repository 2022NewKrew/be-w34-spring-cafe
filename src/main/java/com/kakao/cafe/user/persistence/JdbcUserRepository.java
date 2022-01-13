package com.kakao.cafe.user.persistence;

import com.kakao.cafe.user.domain.entity.User;
import com.kakao.cafe.user.domain.repository.UserRepository;
import com.kakao.cafe.user.persistence.mapper.UserRowMapper;
import com.kakao.cafe.util.MyJdbcTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JdbcUserRepository implements UserRepository {
    private final MyJdbcTemplate myJdbcTemplate;
    private final UserRowMapper userRowMapper;

    @Override
    public Optional<User> getUser(String userId) {
        List<User> users = myJdbcTemplate.query("select * from member where userId = ".concat(userId), userRowMapper);
        if(users.size() == 0){
            return Optional.empty();
        }

        return Optional.of(users.get(0));
    }

    @Override
    public List<User> getAllUsers() {
        return myJdbcTemplate.query("select * from member", userRowMapper);
    }

    @Override
    public void save(User user) {
        myJdbcTemplate.update("insert into member(userId, password, name, email) values(?,?,?,?)"
                , user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
    }
}
