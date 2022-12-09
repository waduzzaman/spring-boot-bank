package com.assignment3.springboot.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.assignment3.springboot.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(
				 "/registration**",
	                "/js/**",
	                "/css/**",
	                "/img/**").permitAll()

				.antMatchers(HttpMethod.GET, "/accounts").permitAll()
				.antMatchers(HttpMethod.GET, "/get-account/**").permitAll()
				.antMatchers(HttpMethod.POST, "/save-account/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/account/**").permitAll()
				.antMatchers(HttpMethod.DELETE, "/account/**").permitAll()

				.antMatchers(HttpMethod.GET, "/users").permitAll()
				.antMatchers(HttpMethod.GET, "/get-user/**").permitAll()
				.antMatchers(HttpMethod.POST, "/save-user/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/user/**").permitAll()
				.antMatchers(HttpMethod.DELETE, "/user/**").permitAll()


				.antMatchers(HttpMethod.GET, "/actypes").permitAll()
				.antMatchers(HttpMethod.GET, "/get-actype/**").permitAll()
				.antMatchers(HttpMethod.POST, "/save-actype").permitAll()
				.antMatchers(HttpMethod.PUT, "/actype/**").permitAll()
				.antMatchers(HttpMethod.DELETE, "/actype/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.permitAll()
		.and()
		.logout()
		.invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login?logout")
		.permitAll();
	}

}
