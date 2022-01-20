package com.kakao.cafe.application.user;

import com.kakao.cafe.application.user.exception.NonExistsUserIdException;
import com.kakao.cafe.domain.user.FindUserPort;
import com.kakao.cafe.domain.user.UpdateUserPort;
import com.kakao.cafe.domain.user.UserVo;

public class UpdateUserService {
    private final FindUserPort findUserPort;
    private final UpdateUserPort updateUserPort;

    public UpdateUserService(FindUserPort findUserPort, UpdateUserPort updateUserPort) {
        this.findUserPort = findUserPort;
        this.updateUserPort = updateUserPort;
    }

    public void updateInformation(UserVo updateUserVo) throws NonExistsUserIdException {
        findUserPort.findByUserId(updateUserVo.getUserId())
                .orElseThrow(() -> new NonExistsUserIdException());

        updateUserPort.update(updateUserVo.convertVoToEntity());
    }
}
