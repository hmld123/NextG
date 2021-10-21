package com.github.hmld.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	@Configuration
	public static class UserSecurityConfig extends WebSecurityConfigurerAdapter{
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().
//			antMatchers("/u/**").hasIpAddress("192.168.123.14").
			anyRequest().authenticated().
			and().formLogin().
			loginPage("/lin").
			permitAll().
			and().logout().logoutUrl("/out").logoutSuccessUrl("/lin?logout").permitAll();
		}	
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("user").password(encoder().encode("123")).authorities("read").build());
		manager.createUser(User.withUsername("gu").password(encoder().encode("123")).authorities("re").build());
		return manager;
	}
	
	@Bean
	public static PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
}
