package com.kakao.cafe;

import com.kakao.cafe.domain.user.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CafeApplication {
	public static List<User> users = new ArrayList<>();

	public static void main(String[] args) {
		// for test
		users.add(new User("user1", "nickname1", "pass1"));
		users.add(new User("user2", "nickname2", "pass2"));
		users.add(new User("user3", "nickname3", "pass3"));

		SpringApplication.run(CafeApplication.class, args);
	}

}
