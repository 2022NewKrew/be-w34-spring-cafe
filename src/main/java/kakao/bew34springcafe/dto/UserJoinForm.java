package kakao.bew34springcafe.dto;

import kakao.bew34springcafe.domain.UserValue.UserId;
import kakao.bew34springcafe.domain.UserValue.UserMail;
import kakao.bew34springcafe.domain.UserValue.UserPw;

public class UserJoinForm {
    private UserId uid;
    private UserMail umail;
    private UserPw upw;


    public UserJoinForm(String uid, String upw, String umail){
        this.uid = new UserId(uid);
        this.upw = new UserPw(upw);
        this.umail = new UserMail(umail);
    }

    public UserId getUid() {
        return this.uid;
    }

    public UserMail getUmail() {
        return this.umail;
    }

    public UserPw getUpw() {
        return this.upw;
    }


    @Override
    public String toString() {
        return "UserJoinForm{" +
                "uid=" + this.uid.toString() +
                ", umail=" + this.umail.toString() +
                ", upw=" + this.upw.toString() +
                '}';
    }
}
