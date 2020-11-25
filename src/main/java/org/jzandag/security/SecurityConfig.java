package org.jzandag.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private AccessDeniedHandler accessDeniedHandler;
	
	protected void configure(HttpSecurity http) throws Exception {

        String[] pagesFree = {"/home", "/template","/layout", "/secured/user/**"};
        
        http.csrf().disable()
            .authorizeRequests()
            	//.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/user/**").hasRole("USER") 
				.antMatchers("/resources/**", "/static/**","/css/**", "/js/**","/img/**").permitAll() 
				.antMatchers(pagesFree).permitAll()
                .anyRequest().authenticated()	
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll().successHandler(successHandler())
                .and()
            .logout()
                .permitAll().logoutSuccessHandler(logoutHandler())
                .and()
            .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

	@Override
	public void configure(WebSecurity web) throws Exception {
//		web
//	        .ignoring()
//	        .antMatchers("/resources/**", "/static/**","/css/**", "/js/**", "/img/**");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
	
	@Bean
	public AuthenticationSuccessHandler successHandler(){
		return new MyAuthSuccessHandler();
	}
	@Bean
	public LogoutSuccessHandler logoutHandler(){
		return new MyAuthLogoutHandler();
	}
}
