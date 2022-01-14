package kakao.bew34springcafe.domain.UserValue;

import java.util.Objects;

public class UserId {
    private String uid;
    public UserId(final String uid){
        this.uid = uid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserId)) return false;
        UserId userId = (UserId) o;
        return Objects.equals(uid, userId.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid);
    }

    @Override
    public String toString() {
        return this.uid;
    }
}
