package com.shalom.dao;

import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shalom.dto.UserDTO;
import com.shalom.entity.User;

@Repository
public class UserDAO implements IUserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User findByUsername(String userName) {
		Session session = sessionFactory.getCurrentSession();
		Query<User> query = session.createQuery("from User where username=:userName", User.class);
		session.setCacheMode(CacheMode.IGNORE);
		query.setParameter("userName", userName);
		
		User user = null;
		try {
			user = query.getSingleResult();
		}catch (Exception e) {
			user = null;
		}
		return user;
	}

	@Override
	public void save(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.setCacheMode(CacheMode.IGNORE);
		session.saveOrUpdate(user);
	}

	@Override
	public User loginUser(UserDTO userDTO) {
		String email = userDTO.getEmail();
		Session session = sessionFactory.getCurrentSession();
		Query<User> query = session.createQuery("from User where email:=userEmail",User.class);
		session.setCacheMode(CacheMode.IGNORE);
		query.setParameter("userEmail", email);
		User user = null;
		try {
			user = query.getSingleResult();
		}catch (Exception e) {
			user = null;
		}
		return user;
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		Session session = sessionFactory.getCurrentSession();
		Query<User> query = session.createQuery("from User where username:=userName,password:=userPassword",User.class);
		session.setCacheMode(CacheMode.IGNORE);
		query.setParameter("userName", username);
		query.setParameter("userPassword", password);
		User user = null;
		try {
			user = query.getSingleResult();
		}catch (Exception e) {
			user = null;
		}
		return user;
	}
	
	

}
