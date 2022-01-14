package kakao.bew34springcafe.db;

import kakao.bew34springcafe.domain.User;
import kakao.bew34springcafe.domain.UserValue.UserId;
import kakao.bew34springcafe.web.UserController;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class UserList {
    public static final List<User> userList = new ArrayList<>();
    private static final Logger logger = Logger.getLogger(UserList.class.getName());

    public static void addUser(User user){
        userList.add(user);
        logger.info("user add log: " + user.toString());
    }

    public static List<User> getUserList(){
        return userList;
    }

    public static User findUserById(UserId uid) throws IllegalArgumentException{
        logger.info(printUserlist());
        for(User user: userList){
            if(user.equalsByUid(uid)) return user;
        }
        throw new IllegalArgumentException("해당 ID를 지닌 유저가 DB에 존재하지 않습니다.");
    }


    public static String printUserlist() {
        StringBuilder stringBuilder = new StringBuilder();
        for( User user: userList){
            stringBuilder.append(user);
        }
        return "UserList DB:\n"+ stringBuilder.toString();
    }
}
