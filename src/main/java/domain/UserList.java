package domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserList {
    private final List<User> userList;

    private UserList(){
        userList = new ArrayList<>();
    }

    private static class LazyHolder{
        private static final UserList USER_LIST =  new UserList();
    }

    public static UserList getInstance(){
        return LazyHolder.USER_LIST;
    }

    public void add(User user) {
        userList.add(user);
    }

    public List<User> getUserList() {
        return userList;
    }

    public User findByUserId(String userId) {
        return userList.stream().filter(user -> user.getUserId().equals(userId)).findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return "UserList{" +
                "userList=" + userList +
                '}';
    }

}
