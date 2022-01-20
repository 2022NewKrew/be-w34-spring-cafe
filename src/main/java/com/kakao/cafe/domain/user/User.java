package com.kakao.cafe.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;


@Getter
@AllArgsConstructor
public class User {

    private final String userId;
    private final String password;
    private final String name;
    private final String email;

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
