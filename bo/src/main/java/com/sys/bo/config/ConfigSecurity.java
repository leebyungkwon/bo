package com.sys.bo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.sys.bo.config.login.LoginFailurHandler;
import com.sys.bo.config.login.LoginSuccessHandler;
import com.sys.bo.task.login.service.LoginService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class ConfigSecurity extends WebSecurityConfigurerAdapter {

	@Autowired private LoginService loginService;




    @Bean
    public PasswordEncoder passwordEncoder() {
    	return NoOpPasswordEncoder.getInstance();
        //return new BCryptPasswordEncoder();
    }


    /*

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }

    @Bean
    public PasswordEncoder delegatingPasswordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    */


    @Override
    public void configure(WebSecurity web) throws Exception
    {
        web.ignoring().antMatchers("/static/plugin/**", "/static/css/**", "/static/js/**", "/static/img/**", "/static/lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
			//.antMatchers("/bo/board/**").hasRole("MASTER")
			//.antMatchers("/bo/**").hasAnyRole("MASTER", "MEMBER")
			// .antMatchers("/bo/mem/boardList").permitAll()
			.antMatchers("/**").permitAll()
			.anyRequest().authenticated();

		http.formLogin()
		.loginPage("/login").permitAll()
		.loginProcessingUrl("/j_spring_security")
		.usernameParameter("memberId")
		.passwordParameter("password")
		//.failureHandler(failurHandler())
		.successHandler(successHandler()).permitAll()
		.failureHandler(failurHandler()).permitAll();

		http.logout().permitAll()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/login")
			.invalidateHttpSession(true);

		http.exceptionHandling().accessDeniedPage("/denied");
		http.csrf().disable();
    }

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(loginService);
	}

	public AuthenticationSuccessHandler successHandler() {
		return new LoginSuccessHandler("/bo/main");
	}
	public AuthenticationFailureHandler failurHandler() {
		return new LoginFailurHandler();
	}

	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
	    return new HttpSessionEventPublisher();
	}

}