package com.kakao.cafe.adapter.out.infra.persistence.user;

import com.kakao.cafe.application.user.dto.LoginRequest;
import com.kakao.cafe.application.user.dto.SignUpRequest;
import com.kakao.cafe.application.user.dto.UpdateRequest;
import com.kakao.cafe.application.user.dto.UserInfo;
import com.kakao.cafe.application.user.dto.UserInfoList;
import com.kakao.cafe.application.user.port.out.GetUserEntityPort;
import com.kakao.cafe.application.user.port.out.LoginUserPort;
import com.kakao.cafe.application.user.port.out.RegisterUserPort;
import com.kakao.cafe.application.user.port.out.UpdateUserInfoPort;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.exceptions.IllegalEmailException;
import com.kakao.cafe.domain.user.exceptions.IllegalPasswordException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserIdException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserNameException;
import com.kakao.cafe.domain.user.exceptions.UserIdDuplicationException;
import com.kakao.cafe.domain.user.exceptions.UserNotExistException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class UserAdapter implements RegisterUserPort, GetUserEntityPort, UpdateUserInfoPort, LoginUserPort {

    private final UserInfoRepository userInfoRepository;

    public UserAdapter(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @Override
    public void registerUser(SignUpRequest signUpRequest)
        throws IllegalUserIdException, IllegalPasswordException, IllegalUserNameException, IllegalEmailException, UserIdDuplicationException {
        checkUserIdDuplication(signUpRequest.getUserId());

        userInfoRepository.save(
            new User.Builder().userId(signUpRequest.getUserId())
                              .password(signUpRequest.getPassword())
                              .name(signUpRequest.getName())
                              .email(signUpRequest.getEmail())
                              .build()
        );
    }

    @Override
    public UserInfoList getAllUsersInfo() {
        List<UserInfo> userInfoList = userInfoRepository.getAllUserList()
                                                        .stream()
                                                        .map(UserInfo::from)
                                                        .collect(Collectors.toList());

        return UserInfoList.from(userInfoList);
    }

    @Override
    public User login(LoginRequest loginRequest) throws UserNotExistException {
        return findUserByUserId(loginRequest.getUserId());
    }

    @Override
    public void updateUser(String userId, UpdateRequest updateRequest)
        throws UserNotExistException, IllegalUserNameException, IllegalEmailException {
        User user = findUserByUserId(userId);

        user.setName(updateRequest.getName());
        user.setEmail(updateRequest.getEmail());
        userInfoRepository.update(user);
    }

    @Override
    public User findUserByUserId(String userId) throws UserNotExistException {
        User user = userInfoRepository.findByUserId(userId).orElse(null);

        if (user == null) {
            throw new UserNotExistException("존재하지 않는 회원입니다.");
        }

        return user;
    }

    private void checkUserIdDuplication(String userId) throws UserIdDuplicationException {
        if (userInfoRepository.findByUserId(userId).isPresent()) {
            throw new UserIdDuplicationException("ID는 중복될 수 없습니다.");
        }
    }
}
