package com.kakao.cafe.user.application;

import com.kakao.cafe.user.application.dto.command.UserAccountCheckCommand;
import com.kakao.cafe.user.application.dto.result.UserAccountCheckResult;
import com.kakao.cafe.user.application.port.in.CheckUserAccountUseCase;
import com.kakao.cafe.user.application.port.out.LoadUserAccountPort;
import com.kakao.cafe.user.domain.UserAccount;
import com.kakao.cafe.user.exception.UserAccountErrorCode;
import com.kakao.cafe.user.exception.UserAccountException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CheckUserAccountService implements CheckUserAccountUseCase {

    private final LoadUserAccountPort loadUserAccountPort;

    @Override
    public UserAccountCheckResult checkPassword(UserAccountCheckCommand command) {
        UserAccount userAccount = loadUserAccountPort.findByEmail(command.getEmail())
                .orElseThrow(() -> new UserAccountException(UserAccountErrorCode.EMAIL_NOT_FOUND));

        return new UserAccountCheckResult(
                userAccount.checkPassword(command.getPassword()),
                userAccount.getUserAccountId());
    }
}
