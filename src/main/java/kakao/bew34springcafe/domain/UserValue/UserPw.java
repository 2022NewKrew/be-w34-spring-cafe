package kakao.bew34springcafe.domain.UserValue;

public class UserPw {
    private String upw="";
    public UserPw (final String upw){
        this.upw = upw;
    }

    @Override
    public String toString() {
        return this.upw;
    }
}
