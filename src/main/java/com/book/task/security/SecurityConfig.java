package com.book.task.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.preauth.x509.SubjectDnX509PrincipalExtractor;
import org.springframework.security.web.authentication.preauth.x509.X509AuthenticationFilter;
import org.springframework.security.web.authentication.preauth.x509.X509PrincipalExtractor;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated());
		http.x509((authorize) -> {
			authorize.x509PrincipalExtractor(x509PrincipalExtractor());
			authorize.x509AuthenticationFilter(x509AuthenticationFilter());
		});
		return http.build();
	}
	
	@Bean
	public X509PrincipalExtractor x509PrincipalExtractor() {
		return new SubjectDnX509PrincipalExtractor();
	}
	
	@Bean
	public X509AuthenticationFilter x509AuthenticationFilter() {
		X509AuthenticationFilter filter = new X509AuthenticationFilter();
		filter.setPrincipalExtractor(x509PrincipalExtractor());
		return filter;
	}
	
}
