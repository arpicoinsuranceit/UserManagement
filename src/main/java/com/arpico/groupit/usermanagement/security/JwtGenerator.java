package com.arpico.groupit.usermanagement.security;

import org.springframework.stereotype.Component;

import com.arpico.groupit.usermanagement.dto.UserTokenDto;
import com.arpico.groupit.usermanagement.util.AppConstant;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerator {
	
	public String generate(UserTokenDto userTokenDto) {
		
		
		
		Claims claims=Jwts.claims().
				setSubject(userTokenDto.getUserCode());
		
		claims.put("userId", userTokenDto.getUserId());
		claims.put("fullName", userTokenDto.getUserFullName());
		claims.put("userCode", userTokenDto.getUserCode());
		claims.put("locCode", userTokenDto.getLocCode());
				
		return Jwts.builder().setClaims(claims)
				.signWith(SignatureAlgorithm.HS512, AppConstant.SECRET_KEY)
				.compact();
		
	}

}
