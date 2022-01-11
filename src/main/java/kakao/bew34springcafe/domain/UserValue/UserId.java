package kakao.bew34springcafe.domain.UserValue;

public class UserId {
    private String uid="";
    public UserId(final String uid){
        this.uid = uid;
    }

    @Override
    public String toString() {
        return this.uid;
    }
}
