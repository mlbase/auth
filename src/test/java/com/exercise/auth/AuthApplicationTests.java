package com.exercise.auth;

import com.exercise.auth.entity.User;
import com.exercise.auth.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class AuthApplicationTests {

	@Autowired
	UserRepository userrepository;

	@Autowired
	PasswordEncoder encoder;

	@Test
	void create() {
		User user = new User();

		long seq = 1;



		user.setId("admin");
		String pwd = encoder.encode("admin");
		user.setPwd(pwd);
		user.setName("관리자");
		user.setNickname("관리자");
		user.setBirthDate("2021-01-01");
		user.setAddress("아마존 서버");


		userrepository.save(user);


	}

	@Test
	void delete(){

		long seq = 2;
		userrepository.deleteAll();


	}

}


