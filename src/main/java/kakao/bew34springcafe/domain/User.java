package kakao.bew34springcafe.domain;

import kakao.bew34springcafe.domain.UserValue.UserMail;
import kakao.bew34springcafe.domain.UserValue.UserId;
import kakao.bew34springcafe.domain.UserValue.UserPw;
import kakao.bew34springcafe.dto.UserJoinForm;

public class User {
    private static UserId uid;
    private static UserPw upw;
    private static UserMail umail;

    public User(UserJoinForm userJoinForm){
        this.uid = userJoinForm.getUid();
        this.umail= userJoinForm.getUmail();
        this.upw = userJoinForm.getUpw();
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + this.uid.toString() + '\'' +
                ", umail='" + this.umail.toString() + '\'' +
                ", upw='" + this.upw.toString() + '\'' +
                '}';
    }

}
