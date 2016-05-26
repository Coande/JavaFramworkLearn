package com.e12e.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.e12e.model.User;

@Repository(value = "userDAO")
public class UserDAO{
	

	@Autowired
	private HibernateTemplate hibernateTemplate;

	public Serializable save(User user) {
		return hibernateTemplate.save(user);
	}

	public void delete(int id) {
		User user=get(id);
		hibernateTemplate.delete(user);
	}

	public void update(User user) {
		hibernateTemplate.update(user);
	}

	public User get(int id) {
		return hibernateTemplate.get(User.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> findByUsername(String username){
		Query query=hibernateTemplate.getSessionFactory().openSession().createQuery("from User where username like :username");
		query.setString("username", "%"+username+"%");
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<User> find() {
		return hibernateTemplate.find("from User");
	}
}
