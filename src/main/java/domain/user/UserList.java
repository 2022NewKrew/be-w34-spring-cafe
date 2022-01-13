package domain.user;

import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ToString
public class UserList {
    private final List<User> userList;

    private UserList(){
        userList = new ArrayList<>();
    }

    public int size() {
        return userList.size();
    }

    private static class LazyHolder{
        private static final UserList USER_LIST =  new UserList();
    }

    public static UserList getInstance(){
        return LazyHolder.USER_LIST;
    }

    public List<User> getCopiedUserList(){
        return new ArrayList<>(userList);
    }

    public void add(User user) {
        userList.add(user);
    }

    public User findByUserId(String userId) {
        return userList.stream().filter(user -> user.getUserId().equals(userId)).findFirst().orElse(null);
    }
}
