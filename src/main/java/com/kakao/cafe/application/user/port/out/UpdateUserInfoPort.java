package com.kakao.cafe.application.user.port.out;

import com.kakao.cafe.application.user.dto.UpdateRequest;
import com.kakao.cafe.domain.user.exceptions.IllegalEmailException;
import com.kakao.cafe.domain.user.exceptions.IllegalPasswordException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserIdException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserNameException;
import com.kakao.cafe.domain.user.exceptions.UserNotExistException;

public interface UpdateUserInfoPort {

    void updateUser(String userId, UpdateRequest updateRequest)
        throws UserNotExistException, IllegalUserIdException, IllegalPasswordException, IllegalUserNameException, IllegalEmailException;
}
