package br.com.dotcom.sbmvc.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired
	private ImplementsUserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("WebSecurityConfig.configure(AuthenticationManagerBuilder auth)");
		//PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    //auth.inMemoryAuthentication().withUser("michelli").password(encoder.encode("123")).roles("ADMIN");
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("WebSecurityConfig.configure(HttpSecurity http)");
    http.csrf().disable()
    	.authorizeRequests()
      	.antMatchers(HttpMethod.GET, "/").permitAll()
      	.antMatchers(HttpMethod.GET, "/fragments/").permitAll()
      	.antMatchers(HttpMethod.GET, "/cadastrarEvento").hasRole("ADMIN")
      	.antMatchers(HttpMethod.POST,"/cadastrarEvento").hasRole("ADMIN")
      	.anyRequest().authenticated()
      	.and()
      .formLogin()
        .loginPage("/acesso")
        .usernameParameter("login")
        .passwordParameter("senha")
        .loginProcessingUrl("/checar")
        //.defaultSuccessUrl("/", true)
        .failureUrl("/acesso?error")
      	.permitAll()
      	.and()
      .logout()
      	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
    ;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		System.out.println("WebSecurityConfig.configure(WebSecurity web)");
    web.ignoring().antMatchers("/materialize/**","/style/**");
	}
	
}
