package com.kakao.cafe.user.application;

import com.kakao.cafe.user.application.dto.command.UserAccountEnrollCommand;
import com.kakao.cafe.user.application.dto.result.UserAccountEnrollResult;
import com.kakao.cafe.user.application.port.in.EnrollUserAccountUseCase;
import com.kakao.cafe.user.application.port.out.LoadUserAccountPort;
import com.kakao.cafe.user.application.port.out.SaveUserAccountPort;
import com.kakao.cafe.user.domain.UserAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnrollUserAccountService implements EnrollUserAccountUseCase {

    private final SaveUserAccountPort saveUserAccountPort;
    private final LoadUserAccountPort loadUserAccountPort;

    @Override
    public UserAccountEnrollResult enroll(UserAccountEnrollCommand command) {
        duplicateCheck(command.getEmail());
        UserAccount userAccount = saveUserAccountPort.save(command.toEntity());
        return new UserAccountEnrollResult(userAccount.getUserAccountId());
    }

    private void duplicateCheck(String email) {
        if (loadUserAccountPort.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("유효하지 않는 값입니다");
        }
    }
}
