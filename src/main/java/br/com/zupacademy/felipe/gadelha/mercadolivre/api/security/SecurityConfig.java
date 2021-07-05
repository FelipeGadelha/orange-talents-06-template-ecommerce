package br.com.zupacademy.felipe.gadelha.mercadolivre.api.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		super.configure(auth);
//	}
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.
//	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/**");
	}
	
	
}
