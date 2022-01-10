package com.kakao.cafe;

import com.kakao.cafe.user.UserRepository;
import com.kakao.cafe.user.UserRepositoryHash;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CafeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CafeApplication.class, args);
	}

}
