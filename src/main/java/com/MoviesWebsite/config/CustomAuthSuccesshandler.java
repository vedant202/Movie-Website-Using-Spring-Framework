package com.MoviesWebsite.config;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.MoviesWebsite.Entities.Users;
import com.MoviesWebsite.Services.UsersServices;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthSuccesshandler implements AuthenticationSuccessHandler {

	@Autowired
	UsersServices userServices;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println(authentication.getPrincipal());
		 Set<String> authoritiesRoles= AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		 System.out.println("oauth authoritiesRoles :- "+authoritiesRoles);
		if(authentication.getPrincipal() instanceof DefaultOAuth2User) {
			DefaultOAuth2User userDetails=(DefaultOAuth2User)authentication.getPrincipal();
			String email = userDetails.getAttribute("email")==null?userDetails.getAttribute("login")+"@gmail.com":userDetails.getAttribute("email");
			String name=userDetails.getAttribute("email")==null?userDetails.getAttribute("login"):userDetails.getAttribute("email");
			
			
			
			System.out.println("Oauth2 google email :- "+email+" and name :- "+name);
			Users getUserByEmail = userServices.getUsersByEmail(email);
			if(getUserByEmail == null) {
				Users u = new Users();
				u.setFname(name.split(" ")[0]);
				u.setLname(name.split(" ")[0]);
				u.setEmail(email);
				u.setPassword(name);
			
				u.setRole("ROLE_USER");
				try {
					userServices.createUser(u);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			/* response.sendRedirect("/"); */
//			new DefaultRedirectStrategy().sendRedirect(request, response, "/");
		}
		 
		 if(authoritiesRoles.contains("ROLE_ADMIN") ) {
			 response.sendRedirect("/admin");
		 }
		 if(authoritiesRoles.contains("ROLE_USER") || authoritiesRoles.contains("OIDC_USER") || authoritiesRoles.contains("OAUTH2_USER")) {
			 response.sendRedirect("/v1/");
		 }
	}

}
