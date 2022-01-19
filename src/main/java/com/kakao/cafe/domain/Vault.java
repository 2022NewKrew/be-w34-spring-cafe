package com.kakao.cafe.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.vault.repository.mapping.Secret;

@Configuration
@Getter
@Setter
public class Vault {

    @Value("")
    String url;
    @Value("")
    String password;
    @Value("")
    String userid;
}
