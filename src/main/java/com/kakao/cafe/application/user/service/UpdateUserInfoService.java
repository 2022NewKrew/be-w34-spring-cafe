package com.kakao.cafe.application.user.service;

import com.kakao.cafe.application.user.dto.UpdateRequest;
import com.kakao.cafe.application.user.port.in.UpdateUserInfoUseCase;
import com.kakao.cafe.application.user.port.out.UpdateUserInfoPort;
import com.kakao.cafe.domain.user.exceptions.IllegalEmailException;
import com.kakao.cafe.domain.user.exceptions.IllegalPasswordException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserIdException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserNameException;
import com.kakao.cafe.domain.user.exceptions.UserNotExistException;

public class UpdateUserInfoService implements UpdateUserInfoUseCase {

    private final UpdateUserInfoPort updateUserInfoPort;

    public UpdateUserInfoService(UpdateUserInfoPort updateUserInfoPort) {
        this.updateUserInfoPort = updateUserInfoPort;
    }

    @Override
    public void updateUserInfo(String userId, UpdateRequest updateRequest)
        throws UserNotExistException, IllegalUserIdException, IllegalPasswordException, IllegalUserNameException, IllegalEmailException {
        updateUserInfoPort.updateUser(userId, updateRequest);
    }
}
