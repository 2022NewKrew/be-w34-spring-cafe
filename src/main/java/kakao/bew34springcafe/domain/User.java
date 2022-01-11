package kakao.bew34springcafe.domain;

import kakao.bew34springcafe.domain.UserValue.UserMail;
import kakao.bew34springcafe.domain.UserValue.UserId;
import kakao.bew34springcafe.domain.UserValue.UserPw;

public class User {
    private static UserId uid;
    private static UserPw upw;
    private static UserMail umail;

    public User(String uid, String umail, String upw){
        this.uid = new UserId(uid);
        this.umail= new UserMail(umail);
        this.upw = new UserPw(upw);
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
