package com.artinrayan.foodi.web.security;

import com.artinrayan.foodi.web.configuration.CsrfHeaderFilter;
import com.artinrayan.foodi.web.security.handler.FoodiAuthenticationSuccessHandler;
import com.artinrayan.foodi.web.security.handler.FoodiLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("customUserDetailsService")
	UserDetailsService userDetailsService;

	@Autowired
	PersistentTokenRepository tokenRepository;

	@Autowired
	FoodiAuthenticationSuccessHandler foodiAuthenticationSuccessHandler;

	@Autowired
	FoodiLogoutSuccessHandler foodiLogoutSuccessHandler;

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		http.addFilterBefore(filter,CsrfFilter.class);



		http.authorizeRequests()
		.antMatchers("/testHost").permitAll().
				antMatchers("/").access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
				.antMatchers("/host/**").access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
				.antMatchers("/user/**").access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
				.antMatchers("/newUser/**", "/delete-user-*").access("hasRole('ADMIN')").antMatchers("/edit-user-*")
				.access("hasRole('ADMIN') or hasRole('DBA')").and().formLogin().loginPage("/login")
				.loginProcessingUrl("/login").usernameParameter("username").passwordParameter("password").and()
				.rememberMe().rememberMeParameter("remember-me").tokenRepository(tokenRepository)
				.tokenValiditySeconds(86400).and()
				.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
				.formLogin().successHandler(foodiAuthenticationSuccessHandler)
				.and().logout().logoutSuccessHandler(foodiLogoutSuccessHandler)
				.and().exceptionHandling().accessDeniedPage("/Access_Denied").and()
				.csrf().csrfTokenRepository(csrfTokenRepository());

		http.authorizeRequests().antMatchers(HttpMethod.GET, "/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.PATCH, "/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/**").permitAll();

//		http
//				.csrf().disable();
//		http
//				.httpBasic()
//				.and()
//				.authorizeRequests()
//				.antMatchers("/index.html", "/home.html", "/login.html", "/").permitAll()
//				.anyRequest().authenticated();
 	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	public PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices() {
		PersistentTokenBasedRememberMeServices tokenBasedservice = new PersistentTokenBasedRememberMeServices(
				"remember-me", userDetailsService, tokenRepository);
		return tokenBasedservice;
	}

	@Bean
	public AuthenticationTrustResolver getAuthenticationTrustResolver() {
		return new AuthenticationTrustResolverImpl();
	}

	private CsrfTokenRepository csrfTokenRepository() {

		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();

		repository.setHeaderName("X-XSRF-TOKEN");

		return repository;

	}

}
