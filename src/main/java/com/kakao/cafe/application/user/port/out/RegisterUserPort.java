package com.kakao.cafe.application.user.port.out;

import com.kakao.cafe.application.user.dto.SignUpRequest;
import com.kakao.cafe.domain.user.exceptions.IllegalEmailException;
import com.kakao.cafe.domain.user.exceptions.IllegalPasswordException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserIdException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserNameException;
import com.kakao.cafe.domain.user.exceptions.UserIdDuplicationException;

public interface RegisterUserPort {

    void registerUser(SignUpRequest signUpRequest)
        throws IllegalUserIdException, IllegalPasswordException, IllegalUserNameException, IllegalEmailException, UserIdDuplicationException;
}
