package com.kakao.cafe.adapter.out.infra.persistence.user;

import com.kakao.cafe.application.user.dto.SignUpRequest;
import com.kakao.cafe.application.user.dto.UserInfo;
import com.kakao.cafe.application.user.dto.UserInfoList;
import com.kakao.cafe.application.user.port.out.GetUserInfoPort;
import com.kakao.cafe.application.user.port.out.RegisterUserPort;
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
public class StoreUserInfoAdapter implements RegisterUserPort, GetUserInfoPort {

    private final UserInfoRepository inMemoryUserInfoRepository;

    public StoreUserInfoAdapter(UserInfoRepository inMemoryUserInfoRepository) {
        this.inMemoryUserInfoRepository = inMemoryUserInfoRepository;
    }

    @Override
    public void registerUser(SignUpRequest signUpRequest)
        throws IllegalUserIdException, IllegalPasswordException, IllegalUserNameException, IllegalEmailException, UserIdDuplicationException {
        checkUserIdDuplication(signUpRequest.getUserId());

        inMemoryUserInfoRepository.save(new User(
            signUpRequest.getUserId(),
            signUpRequest.getPassword(),
            signUpRequest.getName(),
            signUpRequest.getEmail()
        ));
    }

    @Override
    public UserInfoList getAllUsersInfo() {
        List<UserInfo> userInfoList = inMemoryUserInfoRepository.getAllUserList()
                                                                .stream()
                                                                .map(u -> new UserInfo(
                                                                    u.getUserId(),
                                                                    u.getName(),
                                                                    u.getEmail()
                                                                ))
                                                                .collect(Collectors.toList());

        return UserInfoList.from(userInfoList);
    }

    @Override
    public UserInfo findUserByUserId(String userId) throws UserNotExistException {
        User user = inMemoryUserInfoRepository.findByUserId(userId).orElse(null);

        if (user == null) {
            throw new UserNotExistException("존재하지 않는 회원입니다.");
        }

        return new UserInfo(user.getUserId(), user.getName(), user.getEmail());
    }

    private void checkUserIdDuplication(String userId) throws UserIdDuplicationException {
        if (inMemoryUserInfoRepository.findByUserId(userId).isPresent()) {
            throw new UserIdDuplicationException("ID는 중복될 수 없습니다.");
        }
    }
}
