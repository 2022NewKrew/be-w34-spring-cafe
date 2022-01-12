package com.kakao.cafe.adapter.out.infra.persistence.user;

import com.kakao.cafe.application.user.dto.UserInfo;
import com.kakao.cafe.application.user.dto.UsersInfo;
import com.kakao.cafe.application.user.port.out.GetUserInfoPort;
import com.kakao.cafe.application.user.port.out.RegisterUserPort;
import com.kakao.cafe.domain.user.User;
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
    public void registerUser(User user) {
        inMemoryUserInfoRepository.save(UserInfoEntity.from(user));
    }

    @Override
    public UsersInfo getAllUsersInfo() {
        List<UserInfo> userInfoList = inMemoryUserInfoRepository.getAllUserList()
                                                                .stream()
                                                                .map(u -> new UserInfo(
                                                                    u.getUserId(),
                                                                    u.getName(),
                                                                    u.getEmail()
                                                                ))
                                                                .collect(Collectors.toList());

        return UsersInfo.from(userInfoList);
    }

    @Override
    public UserInfo findUserByUserId(String userId) {
        UserInfoEntity userInfoEntity = inMemoryUserInfoRepository.findByUserId(userId)
                                                                  .orElseThrow(RuntimeException::new);        // TODO : 새로운 Exception 정의 필요

        return new UserInfo(
            userInfoEntity.getUserId(),
            userInfoEntity.getName(),
            userInfoEntity.getEmail()
        );
    }
}
