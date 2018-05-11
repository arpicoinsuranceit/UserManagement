package com.arpico.groupit.usermanagement.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.arpico.groupit.usermanagement.dto.UserTokenDto;


@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider{
	
	@Autowired
	JwtValidator validator;
	
	@Override
	public boolean supports(Class<?> arg0) {
		return (JwtAuthenticationToken.class.isAssignableFrom(arg0));
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails arg0, UsernamePasswordAuthenticationToken arg1)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken)
			throws AuthenticationException {
		
		JwtAuthenticationToken authToken=(JwtAuthenticationToken) usernamePasswordAuthenticationToken;
		
		String token=authToken.getToken();
		
		UserTokenDto userTokenDto=validator.validate(token);
		
		if(userTokenDto==null) {
			throw new RuntimeException("JWT Token is incorrect..");
		}
		
		return new JwtUserDetails(userTokenDto.getUserCode(),userTokenDto.getUserFullName(),userTokenDto.getUserId());
	}

}
