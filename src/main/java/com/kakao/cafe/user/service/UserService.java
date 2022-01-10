package com.kakao.cafe.user.service;

import com.kakao.cafe.user.exception.DuplicateUserIdException;
import com.kakao.cafe.user.dto.request.UserCreateRequest;
import com.kakao.cafe.user.dto.response.UserInfoResponse;
import com.kakao.cafe.user.entity.User;
import com.kakao.cafe.user.exception.UserNotFoundException;
import com.kakao.cafe.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    private UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 유저 회원가입 로직
     * @param req: 컨트롤러에 들어온 User 가입 정보
     * @throws DuplicateUserIdException: User 가입 정보 안의 UserId가 이미 존재하는 경우 발생
     * @return Integer: 가입 성공시, PK를 반환해 줌. (사용하든 안하든 맘대로)
     */
    public Integer createUser(UserCreateRequest req) {
        Optional<User> user = this.userRepository.findByUserId(req.getUserId());

        if(user.isPresent()) {
            throw new DuplicateUserIdException();
        }

        User newUser = new User(req);
        return this.userRepository.save(newUser);
    }

    /**
     * 유저 리스트 반환 로직
     */
    public List<UserInfoResponse> getUserList() {
        List<User> userList = this.userRepository.findAll();

        return userList.stream()
                .map(UserInfoResponse::new).collect(Collectors.toUnmodifiableList());
    }

    /**
     * 입력 인자로 들어온 id에 해당하는 User 정보 반환
     * @param id: 원하는 User의 id
     */
    public UserInfoResponse getUserProfile(Integer id) {
        Optional<User> user = this.userRepository.findById(id);

        return new UserInfoResponse(
                user.orElseThrow(UserNotFoundException::new)
        );
    }
}
