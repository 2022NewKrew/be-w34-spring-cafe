package com.kakao.cafe.domain.user;

import com.kakao.cafe.domain.article.ArticleRepository;
import com.kakao.cafe.domain.article.ArticleService;
import com.kakao.cafe.domain.user.dto.UserUpdateForm;
import com.kakao.cafe.domain.user.dto.UserJoinForm;
import com.kakao.cafe.domain.user.dto.UserListDto;
import com.kakao.cafe.core.exception.AlreadyExistId;
import com.kakao.cafe.core.exception.NoSuchUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    public String join(UserJoinForm dtoUser) {
        if(isAlreadyExist(dtoUser)) {
            throw new AlreadyExistId("이미 존재하는 아이디");
        }

        User user = dtoUser.toUser();
        userRepository.save(user);
        return user.getUserId();
    }

    public List<UserListDto> getUserList() {
        List<User> userList = userRepository.findAll();
        return IntStream.range(0, userList.size()).mapToObj(i -> UserListDto.from(userList.get(i), i+1)).collect(Collectors.toList());
    }

    public User findUser(String userId) {
        return userRepository.findByUserId(userId).orElseThrow(() -> new NoSuchUser("그런 사용자는 없습니다."));
    }

    public void updateUser(User user, UserUpdateForm dto) {
        if(!user.getUserId().equals(dto.getUserId())) {
            throw new IllegalArgumentException("다른 사용자의 정보는 수정할 수 없음.");
        }
        if(!user.chcekPassword(dto.getPassword())) {
            throw new IllegalArgumentException("비밀번호 불일치");
        }
        user.updateEmailAndName(dto.getEmail(), dto.getName());
        userRepository.update(user);
        articleRepository.updateAuthorName(user.getId(), user.getName());
    }

    private boolean isAlreadyExist(UserJoinForm dtoUser) {
        return userRepository.findByUserId(dtoUser.getUserId()).isPresent();
    }
}
