package kakao.bew34springcafe.domain.UserValue;


public class UserName {
    private String uname="";
    public UserName (final String uname){
        this.uname = uname;
    }

    @Override
    public String toString() {
        return this.uname;
    }
}
