package com.kakao.cafe;

import com.kakao.cafe.config.RepositoryTestConfig;
import com.kakao.cafe.post.domain.repository.PostRepository;
import com.kakao.cafe.user.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

@JdbcTest
@Import(RepositoryTestConfig.class)
public class JdbcRepositoryTest {
    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected PostRepository postRepository;
}

