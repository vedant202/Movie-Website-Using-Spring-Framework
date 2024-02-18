package com.MoviesWebsite.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	
	@Autowired
	public CustomAuthSuccesshandler successhandler;
	
	@Bean
	public UserDetailsService userDetailsService() {
//		UserDetails user = User.withUsername("vedant").password(passwordEncoder().encode("vedant")).roles("USER").build();
//		UserDetails admin = User.withUsername("admin").password(passwordEncoder().encode("admin")).roles("ADMIN").build();
//		
//		return new InMemoryUserDetailsManager(user,admin);
		
		return new CustomUserDetailsService();

	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		System.out.println(http.);
		http.csrf().disable()
		.authorizeHttpRequests().requestMatchers("/css/**","/js/**","/images/**","/v1/signup","/v1/login").permitAll()
		.requestMatchers("/v1").hasAnyRole("USER","OIDC_USER","ADMIN","OAUTH2_USER")
		.requestMatchers("/admin").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and()
		.oauth2Login()
		.loginPage("/v1/login")
		
		.successHandler(successhandler)
	
		.and()
		
		.formLogin().loginPage("/v1/login").loginProcessingUrl("/v1/login")
		.successHandler(successhandler)
		
		
		.permitAll()
		.and()
		.logout()
		.logoutSuccessUrl("/v1/login")
		.permitAll()
		
		
		;
		
		
		
		return http.build();
	}
	
	
}
