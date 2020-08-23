package org.jzandag;

public class SecurityConfig {
	
	
	protected void configure(/* HttpSecurity http */) throws Exception {
        
        
        String[] pagesFree = {"/", "/home", "/template","/layout"};
        
//        http
//            .authorizeRequests()
//                .antMatchers(pagesFree).permitAll()
//                .anyRequest().authenticated()
//                .and()
//            .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//            .logout()
//                .permitAll();
    }
}
