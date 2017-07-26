package com.artinrayan.foodi.core;

import com.artinrayan.foodi.model.User;
import exception.BusinessException;

import java.util.List;


public interface UserService {
	
	User findById(int id);
	
	User findUserAuthenticateInfoByUsername(String username);

	public User loadUserByUsername(String username) throws BusinessException;

	User findUserByUsername(String username);

	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUserBySSO(String sso);

	List<User> findAllUsers(); 
	
	boolean isUserUnique(Integer id, String sso);

}