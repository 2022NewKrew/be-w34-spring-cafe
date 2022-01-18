package com.kakao.cafe.repository;

import com.kakao.cafe.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository {
    void save(User user);

    List<User> findAll();

    Optional<User> findOne(Integer id);

}
