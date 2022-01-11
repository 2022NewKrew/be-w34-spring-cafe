package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.user.ProfileUpdateDto;
import com.kakao.cafe.dto.user.SimpleUserInfo;
import com.kakao.cafe.dto.user.UserJoinDto;
import com.kakao.cafe.error.exception.duplication.UserEmailDuplicationException;
import com.kakao.cafe.error.exception.duplication.UserNickNameDuplicationException;
import com.kakao.cafe.error.exception.nonexist.UserNotFoundedException;
import com.kakao.cafe.error.msg.UserErrorMsg;
import com.kakao.cafe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public User findByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        return optionalUser.orElseThrow(() -> new UserNotFoundedException(UserErrorMsg.USER_NOT_FOUNDED.getDescription()));
    }

    @Override
    public List<SimpleUserInfo> getListOfSimpleUserInfo(Integer pageNum, Integer pageSize) {
        return userRepository.getListOfSimpleUserInfo(pageNum, pageSize);
    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public User join(UserJoinDto userJoinDTO) {
        if (userRepository.existsByEmail(userJoinDTO.getEmail())) {
            throw new UserEmailDuplicationException(UserErrorMsg.USER_EMAIL_DUPLICATED.getDescription());
        }
        if (userRepository.existsByNickName(userJoinDTO.getNickName())) {
            throw new UserNickNameDuplicationException(UserErrorMsg.USER_NICKNAME_DUPLICATED.getDescription());
        }
        User newUser = userJoinDTO.toUserWithCurrentDate();
        return userRepository.save(newUser);
    }

    @Override
    public User updateProfile(ProfileUpdateDto profileUpdateDto) {
        Long targetUserId = profileUpdateDto.getId();
        User targetUser = findById(targetUserId);
        checkDuplicationForNewNickName(profileUpdateDto.getNickName(), targetUser.getNickName());
        profileUpdateDto.updateUser(targetUser);
        return userRepository.save(targetUser);
    }

    private void checkDuplicationForNewNickName(String newNickName, String targetUserNickName) {
        if (userRepository.existsByNickName(newNickName) && !newNickName.equals(targetUserNickName)) {
            throw new UserNickNameDuplicationException(UserErrorMsg.USER_NICKNAME_DUPLICATED.getDescription());
        }
    }

    @Override
    public int countAll() {
        return userRepository.countAll();
    }
}
