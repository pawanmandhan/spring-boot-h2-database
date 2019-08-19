package com.demo.h2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.h2.dao.UserRepository;
import com.demo.h2.models.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootH2Application.class)
public class SpringBootH2UnitTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void givenUserProfile_whenAddUser_thenCreateNewUser() {
		User user = new User();
		user.setFirstName("John");
		user.setLastName("Doe");
		userRepository.save(user);
		List<User> users = (List<User>) userRepository.findAll();
		assertFalse(users.isEmpty());

		String firstName = "Aliko";
		String lastName = "Dangote";
		User user1 = userRepository.findOne(users.get(0).getId());
		user1.setLastName(lastName);
		user1.setFirstName(firstName);
		userRepository.save(user1);

		user = userRepository.findOne(user.getId());
		assertEquals(user.getFirstName(), firstName);
		assertEquals(user.getLastName(), lastName);

		userRepository.delete(user.getId());
		assertTrue(((List<User>) userRepository.findAll()).isEmpty());
	}

}
