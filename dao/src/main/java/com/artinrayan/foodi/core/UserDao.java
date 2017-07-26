package com.artinrayan.foodi.core;


import com.artinrayan.foodi.model.User;
import exceptions.UserDaoException;

import java.util.List;


public interface UserDao {

	User findById(int id) throws UserDaoException;
	
	User findUserAuthenticateInfoByUsername(String username) throws UserDaoException;

	User loadUserByUsername(String username) throws UserDaoException;

	User findUserByUsername(String username) throws UserDaoException;

	void save(User user) throws UserDaoException;
	
	void deleteBySSO(String sso) throws UserDaoException;
	
	List<User> findAllUsers() throws UserDaoException;

}

