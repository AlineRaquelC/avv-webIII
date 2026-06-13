package com.comunicacao.api.seguranca;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	private final String segredo = "automanager-webii-segredo";
	private final long validade = 1000 * 60 * 60;

	public String gerarToken(String login, String perfil) {
		return Jwts.builder()
				.setSubject(login)
				.claim("perfil", perfil)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + validade))
				.signWith(SignatureAlgorithm.HS512, segredo)
				.compact();
	}

	public Claims obterClaims(String token) {
		return Jwts.parser()
				.setSigningKey(segredo)
				.parseClaimsJws(token)
				.getBody();
	}

	public boolean tokenValido(String token) {
		try {
			Claims claims = obterClaims(token);
			return claims.getExpiration().after(new Date());
		} catch (Exception e) {
			return false;
		}
	}

	public String obterLogin(String token) {
		return obterClaims(token).getSubject();
	}
}
