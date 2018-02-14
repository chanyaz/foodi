package com.artinrayan.foodi.web.security;

import com.artinrayan.foodi.model.CurrentUser;
import com.artinrayan.foodi.model.User;
import com.artinrayan.foodi.model.UserProfile;
import com.artinrayan.foodi.core.UserService;
import exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{

	static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Autowired
	private UserService userService;

	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = null;
		try {
			user = userService.loadUserByUsername(username);
		} catch (BusinessException e) {
			throw new UsernameNotFoundException(e.getMessage());
		}
		logger.info("User : {}", user);
		if(user == null){
			logger.info("User not found");
			throw new UsernameNotFoundException("Username not found");
		}
//			return user;
			return new CurrentUser(user.getUsername(), user.getPassword(),
				 true, true, true, true, getGrantedAuthorities(user), user.getId(),
					(user.getFirstName() + " " + user.getLastName()));

	}


	private List<GrantedAuthority> getGrantedAuthorities(User user){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for(UserProfile userProfile : user.getUserProfiles()){
			logger.info("UserProfile : {}", userProfile);
			authorities.add(new SimpleGrantedAuthority("ROLE_"+userProfile.getType()));
		}
		logger.info("authorities : {}", authorities);
		return authorities;
	}

}
