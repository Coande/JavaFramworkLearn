package com.e12e.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.e12e.dao.UserDAO;
import com.e12e.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/applicationContext.xml" })
public class TestUserDAO {
	User user;

	@Autowired
	UserDAO userDAO;

	@Before
	public void initTest() {
		user = new User();
		user.setUsername("Coande223");
		user.setPassword("password1");
		user.setEmail("e12e01@qq.com");
		user.setSex("男");
		user.setAge(20);
	}

	@Test
	public void testSave() {

		userDAO.save(user);
	}

	@Test
	public void testDelete() {
		userDAO.delete(1);
	}

	@Test
	public void testUpdate() {
		userDAO.update(user);
	}

	@Test
	public void testGet() {
		User user = userDAO.get(1);
		System.out.println(user.getUsername());
	}

	@Test
	public void testFind() {
		List<User> users = userDAO.find();
		for (User user : users) {
			System.out.println(user.getUsername() + "-------" + user.getPassword());
		}
	}

	@Test
	public void testFindByUsername() {
		List<User> users = userDAO.findByUsername("coande");
		for (User user : users) {
			System.out.println(user.getUsername() + "-------" + user.getPassword());
		}
	}

}
