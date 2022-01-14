package com.kakao.cafe.infra.repository.user;

import com.kakao.cafe.web.user.form.ModifyingUserInfo;
import com.kakao.cafe.web.user.form.UserProfileInfo;
import com.kakao.cafe.infra.dao.UserCreateCommand;
import com.kakao.cafe.infra.dao.UserDAO;
import com.kakao.cafe.web.user.form.UserInventoryInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final UserDAO userDAO;

    public UserRepositoryImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void saveUser(UserCreateCommand userCreateCommand) {
        userDAO.saveUser(userCreateCommand);
    }

    @Override
    public void updateUser(Long userId, String name, String email, String password) {
        userDAO.updateUser(userId, name, email, password);
    }

    @Override
    public List<UserInventoryInfo> findUserInventoryInfo() {
        List<UserCreateCommand> userCreateCommands = userDAO.findAll();
        return IntStream.range(0, userCreateCommands.size())
                .mapToObj(i -> {
                    UserCreateCommand userCreateCommand = userCreateCommands.get(i);
                    return new UserInventoryInfo(i + 1,
                            userCreateCommand.getId(),
                            userCreateCommand.getNickname(),
                            userCreateCommand.getName(),
                            userCreateCommand.getEmail());
                }).collect(Collectors.toList());
    }

    @Override
    public UserProfileInfo findUserProfileInfo(Long userId) {
        Optional<UserProfileInfo> optionalUserProfileInfo = userDAO.findUserProfileInfo(userId);
        optionalUserProfileInfo.orElseThrow(() -> new RuntimeException("해당 하는 유저를 찾을 수 없습니다."));
        return optionalUserProfileInfo.get();
    }

    @Override
    public ModifyingUserInfo findModifyingUserForm(Long userId) {
        Optional<ModifyingUserInfo> optionalModifyingUserInfo = userDAO.findModifyingUserForm(userId);
        optionalModifyingUserInfo.orElseThrow(() -> new RuntimeException("해당 하는 유저를 찾을 수 없습니다."));
        return optionalModifyingUserInfo.get();
    }

    /**
     * To-do
     */
    @Override
    public String findPasswordAndIdByNickname(String nickname) {
        return null;
    }

    @Override
    public Boolean isAlreadyExistNickname(String nickname) {
        Optional<String> optionalNickname = userDAO.findNicknameByExpectedNickname(nickname);
        return optionalNickname.isPresent();
    }
}
