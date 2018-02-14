package com.artinrayan.foodi.core;

import com.artinrayan.foodi.model.User;
import exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("userService")
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;

	@Autowired
	private EmailService emailService;

	@Autowired
    private PasswordEncoder passwordEncoder;
	
	public User findByUserId(int id) {
		return dao.findById(id);
	}

	public User findUserAuthenticateInfoByUsername(String username) {
		User user = dao.findUserAuthenticateInfoByUsername(username);
		return user;
	}

	public User loadUserByUsername(String username) throws BusinessException
	{
		return dao.loadUserByUsername(username);
	}

	public User findUserByUsername(String username) {
		User user = dao.findUserByUsername(username);
		user.getUserHosts();
		return user;
	}

	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		dao.save(user);
		emailService.sendMail(user);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateUser(User user) {
		User entity = dao.findById(user.getId());
		if(entity!=null){
			entity.setUsername(user.getUsername());
			if(!user.getPassword().equals(entity.getPassword())){
				entity.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setEmail(user.getEmail());
			entity.setUserProfiles(user.getUserProfiles());
		}
	}

	
	public void deleteUserBySSO(String sso) {
		dao.deleteBySSO(sso);
	}

	public List<User> findAllUsers() {
		return dao.findAllUsers();
	}

	public boolean isUserUnique(Integer id, String username) {
		User user = findUserAuthenticateInfoByUsername(username);
		return ( user == null || ((id != null) && (user.getId() == id)));
	}
	
}
