package com.kakao.cafe.user.service;

import com.kakao.cafe.user.dto.request.UserLoginRequest;
import com.kakao.cafe.user.dto.request.UserUpdateRequest;
import com.kakao.cafe.user.exception.DuplicateUserIdException;
import com.kakao.cafe.user.dto.request.UserCreateRequest;
import com.kakao.cafe.user.dto.response.UserInfoResponse;
import com.kakao.cafe.user.entity.User;
import com.kakao.cafe.user.exception.UserIdPasswordNotMatchedException;
import com.kakao.cafe.user.exception.UserNotFoundException;
import com.kakao.cafe.user.mapper.UserMapper;
import com.kakao.cafe.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /**
     * 유저 회원가입 로직
     * @param req: 컨트롤러에 들어온 User 가입 정보
     * @throws DuplicateUserIdException: User 가입 정보 안의 UserId가 이미 존재하는 경우 발생
     */
    public void createUser(UserCreateRequest req) {
        Optional<User> user = this.userRepository.findByUserId(req.getUserId());

        if(user.isPresent()) {
            throw new DuplicateUserIdException();
        }

        User newUser = userMapper.userCreateRequestToEntity(req);
        this.userRepository.save(newUser);
    }

    /**
     * 유저 리스트 반환 로직
     * @return List<UserInfoResponse>: 유저 정보 DTO 로 이루어진 리스트
     */
    public List<UserInfoResponse> getUserList() {
        List<User> userList = this.userRepository.findAll();

        return userList.stream()
                                .map(userMapper::userToUserInfoResponse)
                                .collect(Collectors.toUnmodifiableList());
    }

    /**
     * 입력 인자로 들어온 id에 해당하는 User 정보 반환
     * @param id: 원하는 User 의 id
     * @throws UserNotFoundException: 해당 ID 의 사용자가 없을 경우 발생
     * @return UserInfoResponse: 유저 정보 DTO
     */
    public UserInfoResponse getUserProfile(Long id) {
        Optional<User> user = this.userRepository.findById(id);

        return userMapper.userToUserInfoResponse(
                user.orElseThrow(UserNotFoundException::new)
        );
    }

    /**
     * userId가 입력 인자로 들어온 userId와 같고, passsword가 입력 인자로 들어온 password와 같은 User 정보 반환
     * @param req: 원하는 User의 userId/password
     * @return UserInfoResponse: 유저 정보 반환
     */
    public UserInfoResponse getLoginUserProfile(UserLoginRequest req) {
        Optional<User> user = this.userRepository.findByUserIdAndPassword(req.getUserId(), req.getPassword());

        return userMapper.userToUserInfoResponse(
                user.orElseThrow(UserIdPasswordNotMatchedException::new)
        );
    }

    /**
     * 입력 인자로 들어온 id에 해당하는 User의 정보를 수정하는 로직
     * @param id: 수정할 사용자의 ID(PK)
     * @param req: 회원 프로필 수정 정보
     */
    public void updateUser(Long id, UserUpdateRequest req) {
        User user = this.userRepository.findById(id)
                                       .orElseThrow(UserNotFoundException::new);

        if(!user.checkPassword(req.getPasswordCheck())) {
            throw new UserIdPasswordNotMatchedException();
        }
        
        this.changeUserInfo(user, req);

        this.userRepository.update(user);
    }

    private void changeUserInfo(User user, UserUpdateRequest req) {
        if(!req.getNewPassword().isBlank()) {
            user.setPassword(req.getNewPassword());
        }
        if(!req.getName().isBlank()) {
            user.setName(req.getName());
        }
        if(!req.getEmail().isBlank()) {
            user.setEmail(req.getEmail());
        }
    }
}
