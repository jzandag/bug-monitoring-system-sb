package org.jzandag.utils;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.jzandag.model.Users;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 
 * @author John Zidrex M. Andag
 *
 */

public class BMUtils {
	
	public static boolean isNull(Object object) {
		return (object == null);
	}
	
	
	
	// User logged methods
	public static Users getLoggedUser() {
		return null;
	}
	
	/*
	 * Get current user session
	*/
	public static Users getCurrentUserSession(HttpServletRequest request) {
		Users u = (Users) request.getSession().getAttribute("userSessionObj");	
		return u;
	}
	
	/*
	 * Get auth object
	*/
	public static Authentication getAuth() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	/*
	 * Get collection of granted authorities 
	*/
	public static Collection<? extends GrantedAuthority> getAuthorities() {
		return SecurityContextHolder.getContext().getAuthentication().getAuthorities();
	}
	
}
