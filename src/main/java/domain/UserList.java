package domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserList {
    private final List<User> userList;

    public UserList() {
        userList = new ArrayList<>();
    }
    public void add(User user){
        userList.add(user);
    }

    public List<User> getUserList(){
        return userList;
    }

    public User findByUserId(String userId) {
        User findUser = userList.stream().filter(user -> user.getUserId().equals(userId)).findFirst().orElse(null);
        return findUser;
    }

    @Override
    public String toString() {
        return "UserList{" +
                "userList=" + userList +
                '}';
    }

}
