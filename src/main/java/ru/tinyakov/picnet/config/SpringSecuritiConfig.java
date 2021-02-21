package ru.tinyakov.picnet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SpringSecuritiConfig extends WebSecurityConfigurerAdapter {

    private final AccessDeniedHandler accessDeniedHandler;
    private final DataSource dataSource;

    public SpringSecuritiConfig(AccessDeniedHandler accessDeniedHandler, DataSource dataSource) {
        this.accessDeniedHandler = accessDeniedHandler;
        this.dataSource = dataSource;
    }

   /* @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/pic/**", "/about","/user/**","/home").permitAll()
                .and()
                .authorizeRequests().antMatchers("/me/**").authenticated()
                .and()
                .authorizeRequests().antMatchers("/admin","/admin/**").hasAnyRole("ADMIN")
//                .authorizeRequests().antMatchers("/admin/**").hasAnyRole( "ADMIN", "USER" )

//                .antMatchers("/admin/**","/simple").hasAnyRole("ADMIN")
//                .antMatchers("/user/**").hasAnyRole("USER")
//                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
//                .permitAll()
                .and()
                .logout().logoutUrl("/logout");
//                .permitAll()
//                .and()
//                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth)
//            throws Exception {
//        auth.userDetailsService(PicnetUserDetailsService)
////                .jdbcAuthentication()
////                .dataSource(dataSource)
////                .usersByUsernameQuery("select email,pass "
////                        + "from users "
////                        + "where email = ?")
////                .authoritiesByUsernameQuery("select email,role "
////                        + "from authorities "
////                        + "where email = ?");
//    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
////        auth.inMemoryAuthentication()
////                .withUser("user").password("password").roles("USER")
////                .and()
////                .withUser("admin").password("password").roles("ADMIN");
//    }

    //Spring Boot configured this already.
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
            .antMatchers("/resources/**", "/static/**", "/static/css/**", "/js/**", "/images/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
//        return new BCryptPasswordEncoder();
    }
}
