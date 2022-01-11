package kakao.bew34springcafe.dto;

import kakao.bew34springcafe.domain.UserValue.UserId;
import kakao.bew34springcafe.domain.UserValue.UserMail;
import kakao.bew34springcafe.domain.UserValue.UserName;
import kakao.bew34springcafe.domain.UserValue.UserPw;

public class UserJoinForm {
    private UserId uid;
    private UserMail umail;
    private UserPw upw;

    public void UserJoinForm(UserId uid, UserPw upw, UserMail umail){
        this.uid = uid;
        this.upw = upw;
        this.umail = umail;
    }

    @Override
    public String toString() {
        return "UserJoinForm{" +
                "uid=" + uid +
                ", umail=" + umail +
                ", upw=" + upw +
                '}';
    }
}
