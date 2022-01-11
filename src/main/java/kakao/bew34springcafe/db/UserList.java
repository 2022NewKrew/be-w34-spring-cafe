package kakao.bew34springcafe.db;

import kakao.bew34springcafe.domain.User;
import kakao.bew34springcafe.web.UserController;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class UserList {
    public static final List<User> userList = new ArrayList<User>();
    private static final Logger logger = Logger.getLogger(UserList.class.getName());

    public static void addUser(User user){
        userList.add(user);
        logger.info("user add log: " + user.toString());
    }

    public static List<User> getUserList(){
        return userList;
    }
}
