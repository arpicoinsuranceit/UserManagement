package com.arpico.groupit.usermanagement.security;

import org.springframework.stereotype.Component;

import com.arpico.groupit.usermanagement.dto.UserTokenDto;
import com.arpico.groupit.usermanagement.util.AppConstant;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtValidator {
	
	
	public UserTokenDto validate(String token) {
		UserTokenDto userTokenDto=null;
		try {
		
		Claims claims=Jwts.parser().setSigningKey(AppConstant.SECRET_KEY).parseClaimsJws(token)
				.getBody();
		
		userTokenDto=new UserTokenDto();
		userTokenDto.setUserCode(claims.getSubject());
		userTokenDto.setUserId(Integer.parseInt((String) claims.get("loginId")));
		userTokenDto.setUserFullName((String) claims.get("password"));
		}catch (Exception e) {
			System.out.println(e);
		}
		
		return userTokenDto;
		
	}
	
	

}
