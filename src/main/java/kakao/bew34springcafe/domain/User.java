package kakao.bew34springcafe.domain;

import kakao.bew34springcafe.domain.UserValue.UserMail;
import kakao.bew34springcafe.domain.UserValue.UserId;
import kakao.bew34springcafe.domain.UserValue.UserName;
import kakao.bew34springcafe.domain.UserValue.UserPw;

public class User {
    private UserId uid;
    private UserPw upw;
    private UserName uname;
    private UserMail umail;

    public User(UserId uid, UserPw upw, UserName uname, UserMail umail){
        setUid(uid);
        setUpw(upw);
        setUname(uname);
        setUmail(umail);
    }



    //getter
    public UserId getUid() {
        return uid;
    }

    public UserPw getUpw() {
        return upw;
    }

    public UserName getUname() {
        return uname;
    }

    public UserMail getUmail() {
        return umail;
    }

    //setter
    public void setUid(UserId uid) {
        this.uid = uid;
    }

    public void setUpw(UserPw upw) {
        this.upw = upw;
    }

    public void setUname(UserName uname) {
        this.uname = uname;
    }

    private void setUmail(UserMail umail) {
        this.umail = umail;

    }
}
