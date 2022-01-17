package com.kakao.cafe.service.user;

import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.entity.UserEntity;
import com.kakao.cafe.exception.user.IncorrectPasswordException;
import com.kakao.cafe.exception.user.LoginFailedException;
import com.kakao.cafe.mapper.UserMapper;
import com.kakao.cafe.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public UserDto register(UserDto userDto) {
        UserEntity userEntity = userMapper.toUserEntity(userDto);
        UserEntity savedEntity = userRepository.save(userEntity);

        return userMapper.toUserDto(savedEntity);
    }

    @Override
    public UserDto login(UserDto userDto) throws Exception {
        String email = userDto.getEmail();
        String password = userDto.getPassword();

        try {
            UserEntity userEntity = userRepository.findByEmail(email);
            if (password.equals(userEntity.getPassword())) {
                return userMapper.toUserDto(userEntity);
            }

            throw new IncorrectPasswordException("비밀번호가 일치하지 않습니다. 다시 시도해주세요.");
        } catch (IncorrectPasswordException e) {
            throw e;
        } catch (Exception e) {
            throw new LoginFailedException("로그인에 실패하였습니다. 다시 시도해주세요.");
        }
    }

    @Override
    public List<UserDto> allUsers() {
        List<UserEntity> userEntities = userRepository.findAll();

        return userMapper.toUserDtoList(userEntities);
    }
}
