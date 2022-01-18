package com.kakao.cafe.user.application;

import com.kakao.cafe.user.application.dto.command.UserAccountDetailEmailCommand;
import com.kakao.cafe.user.application.dto.command.UserAccountDetailIdCommand;
import com.kakao.cafe.user.application.dto.result.UserAccountDetailListResult;
import com.kakao.cafe.user.application.dto.result.UserAccountDetailResult;
import com.kakao.cafe.user.application.port.in.GetUserAccountUseCase;
import com.kakao.cafe.user.application.port.out.LoadUserAccountPort;
import com.kakao.cafe.user.domain.UserAccount;
import com.kakao.cafe.user.exception.UserAccountErrorCode;
import com.kakao.cafe.user.exception.UserAccountException;
import com.kakao.cafe.util.DateTimeFormatUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetUserAccountService implements GetUserAccountUseCase {

    private final LoadUserAccountPort loadUserAccountPort;

    @Override
    public UserAccountDetailResult getUserInfoByEmail(UserAccountDetailEmailCommand command) {
        UserAccount userAccount = loadUserAccountPort.findByEmail(command.getEmail())
                .orElseThrow(() -> new UserAccountException(UserAccountErrorCode.EMAIL_NOT_FOUND));

        return new UserAccountDetailResult(
                userAccount.getUserAccountId(),
                userAccount.getUsername(),
                userAccount.getEmail(),
                userAccount.getPassword(),
                DateTimeFormatUtil.format(userAccount.getCreatedAt()));
    }

    @Override
    public UserAccountDetailListResult getAllUser() {
        List<UserAccountDetailResult> result = loadUserAccountPort.findAll().stream()
                .map(user -> new UserAccountDetailResult(
                        user.getUserAccountId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getPassword(),
                        DateTimeFormatUtil.format(user.getCreatedAt())
                )).collect(Collectors.toList());

        return new UserAccountDetailListResult(result);
    }

    @Override
    public UserAccountDetailResult getUserInfo(UserAccountDetailIdCommand command) {
        UserAccount userAccount = loadUserAccountPort.findById(command.getId())
                .orElseThrow(() -> new UserAccountException(UserAccountErrorCode.ID_NOT_FOUND));

        return new UserAccountDetailResult(
                userAccount.getUserAccountId(),
                userAccount.getUsername(),
                userAccount.getEmail(),
                userAccount.getPassword(),
                DateTimeFormatUtil.format(userAccount.getCreatedAt()));
    }
}
