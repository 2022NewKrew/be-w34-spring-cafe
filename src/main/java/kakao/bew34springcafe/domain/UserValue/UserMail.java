package kakao.bew34springcafe.domain.UserValue;

public class UserMail {
    private String umail = "";
    public UserMail(final String umail){
        this.umail= umail;
    }

    @Override
    public String toString() {
        return this.umail;
    }
}
