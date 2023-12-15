package com.jdc.shop.security;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;

@Service
public class JwtTokenProvider {
	
	@Value("${app.jwt.token.issuer}")
	private String issuer;
	@Value("${app.jwt.token.life}")
	private int tokenLife;
	
	private SecretKey signKey = Jwts.SIG.HS512.key().build();
	
	public Optional<String> generateToken(Authentication authentication) {
		
		var issueAt = new Date();
		var calendar = Calendar.getInstance();
		calendar.setTime(issueAt);
		calendar.add(Calendar.MINUTE, tokenLife);
		
		var token = Jwts.builder()
			.signWith(signKey)
			.subject(authentication.getName())
			.claim("role", authentication.getAuthorities()
					.stream().map(a -> a.getAuthority())
					.collect(Collectors.joining(",")))
			.issuer(issuer)
			.issuedAt(issueAt)
			.expiration(calendar.getTime()).compact();
		
		return Optional.ofNullable(token);
	}

	public Authentication parseToken(String token) {
		
		var parser =Jwts.parser().requireIssuer(issuer)
			.verifyWith(signKey)
			.build();
		
		var jws = parser.parseSignedClaims(token);
		
		var username = jws.getPayload().getSubject();
		var roles = jws.getPayload().get("role").toString();
		
		var authorities = Arrays.stream(roles.split(","))
				.map(a -> new SimpleGrantedAuthority(a)).toList();
		
		return UsernamePasswordAuthenticationToken.authenticated(username, null, authorities);
	}

}
