package com.kakao.cafe.application.user;

import com.kakao.cafe.domain.user.FindUserPort;
import com.kakao.cafe.domain.user.UpdateUserPort;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserVo;

import java.util.Optional;

public class UpdateUserService {

    private final FindUserPort findUserPort;
    private final UpdateUserPort updateUserPort;

    public UpdateUserService(FindUserPort findUserPort, UpdateUserPort updateUserPort) {
        this.findUserPort = findUserPort;
        this.updateUserPort = updateUserPort;
    }

    public void updateInformation(UserVo updateUserVo) throws IllegalArgumentException {
        System.out.println(updateUserVo);
        Optional<User> optionalUser = findUserPort.findByUserId(updateUserVo.getUserId());
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 사용자의 정보를 수정할 수 없습니다.");
        }

        updateUserPort.save(updateUserVo.convertVoToEntity());
    }

}
