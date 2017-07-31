package com.artinrayan.foodi.core;

import com.artinrayan.foodi.model.User;
import exception.BusinessException;

import java.util.List;


public interface UserService {
	
	User findByUserId(int id) throws BusinessException;
	
	User findUserAuthenticateInfoByUsername(String username)throws BusinessException;

	public User loadUserByUsername(String username) throws BusinessException;

	User findUserByUsername(String username) throws BusinessException;

	void saveUser(User user) throws BusinessException;
	
	void updateUser(User user) throws BusinessException;
	
	void deleteUserBySSO(String sso) throws BusinessException;

	List<User> findAllUsers() throws BusinessException;
	
	boolean isUserUnique(Integer id, String sso) throws BusinessException;

}