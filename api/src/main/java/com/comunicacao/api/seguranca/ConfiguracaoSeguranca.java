package com.comunicacao.api.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class ConfiguracaoSeguranca {

	@Autowired
	private FiltroJwt filtroJwt;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
				.antMatchers("/login").permitAll()
				.antMatchers("/api/**").authenticated()
				.anyRequest().permitAll()
				.and()
				.httpBasic().disable()
				.formLogin().disable();

		http.addFilterBefore(filtroJwt, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}
