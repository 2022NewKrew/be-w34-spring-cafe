package com.kakao.cafe.user.persistence;

import com.kakao.cafe.user.domain.entity.User;
import com.kakao.cafe.user.domain.entity.UserInfo;
import com.kakao.cafe.user.domain.repository.UserRepository;
import com.kakao.cafe.user.persistence.mapper.UserRowMapper;
import com.kakao.cafe.util.MyJdbcTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JdbcUserRepository implements UserRepository {
    private final MyJdbcTemplate myJdbcTemplate;
    private final UserRowMapper userRowMapper;

    @Override
    public Optional<User> getUser(String userId) {
        List<User> users = myJdbcTemplate.query(String.format("select * from member where userId = '%s'",userId), userRowMapper);
        if(users.isEmpty()){
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
        if(getUser(user.getUserId()).isPresent()){
            throw new IllegalStateException("이미 사용자가 있어서 저장하지 못합니다.");
        }

        myJdbcTemplate.update("insert into member(userId, password, name, email) values(?,?,?,?)"
                , user.getUserId(), user.getPassword(), user.getUserInfo().getName(), user.getUserInfo().getEmail());
    }

    @Override
    @Transactional
    public void update(String userId, UserInfo userInfo) {
        List<User> users = myJdbcTemplate.query("select * from member where userId='".concat(userId).concat("'"), userRowMapper);
        if(users.isEmpty()){
            throw new IllegalStateException("해당하는 유저가 없어 변경하지 못 했습니다.");
        }

        users.get(0).updateInfo(userInfo);
        myJdbcTemplate.update("update member set name='".concat(userInfo.getName()).concat("', email='").concat(userInfo.getEmail())
                .concat("' where userId = '").concat(userId).concat("'"));
    }
}
