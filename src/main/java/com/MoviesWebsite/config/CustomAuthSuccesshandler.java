package com.MoviesWebsite.config;

import java.io.IOException;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthSuccesshandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		 Set<String> authoritiesRoles= AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		 
		 if(authoritiesRoles.contains("ROLE_ADMIN")) {
			 response.sendRedirect("/admin");
		 }
		 if(authoritiesRoles.contains("ROLE_USER")) {
			 response.sendRedirect("/v1/");
		 }
	}

}
