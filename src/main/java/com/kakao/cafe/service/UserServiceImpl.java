package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserJoinDTO;
import com.kakao.cafe.error.exception.duplication.UserEmailDuplicationException;
import com.kakao.cafe.error.exception.duplication.UserNickNameDuplicationException;
import com.kakao.cafe.error.exception.nonexist.UserNotFoundedException;
import com.kakao.cafe.error.msg.UserErrorMsg;
import com.kakao.cafe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElseThrow(() -> new UserNotFoundedException(UserErrorMsg.USER_NOT_FOUNDED.getDescription()));
    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public User join(UserJoinDTO userJoinDTO) {
        if (userRepository.existsByEmail(userJoinDTO.getEmail())) {
            throw new UserEmailDuplicationException(UserErrorMsg.USER_EMAIL_DUPLICATED.getDescription());
        }
        if (userRepository.existsByNickName(userJoinDTO.getNickName())) {
            throw new UserNickNameDuplicationException(UserErrorMsg.USER_NICKNAME_DUPLICATED.getDescription());
        }
        User newUser = userJoinDTO.toUser();
        return userRepository.save(newUser);
    }
}
