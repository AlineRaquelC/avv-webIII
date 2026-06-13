package com.comunicacao.api.seguranca;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;

@Component
public class FiltroJwt extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authorization = request.getHeader("Authorization");

		if (authorization != null && authorization.startsWith("Bearer ")) {
			String token = authorization.substring(7);

			if (jwtUtil.tokenValido(token)) {
				Claims claims = jwtUtil.obterClaims(token);
				String login = claims.getSubject();
				String perfil = claims.get("perfil", String.class);

				UsernamePasswordAuthenticationToken autenticacao =
						new UsernamePasswordAuthenticationToken(
								login,
								null,
								Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + perfil)));

				SecurityContextHolder.getContext().setAuthentication(autenticacao);
			}
		}

		filterChain.doFilter(request, response);
	}
}
