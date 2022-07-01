package redditclone.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureAuthentication(
            AuthenticationManagerBuilder authenticationManagerBuilder)
            throws Exception {

        authenticationManagerBuilder
                .userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthenticationTokenFilter authenticationTokenFilterBean()
            throws Exception {
        AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
        authenticationTokenFilter
                .setAuthenticationManager(authenticationManagerBean());
        return authenticationTokenFilter;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.headers().cacheControl().disable();
        httpSecurity.cors();
        httpSecurity.headers().frameOptions().disable();
        httpSecurity.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/users/").permitAll()
                .antMatchers(HttpMethod.POST, "/users/").permitAll()
                .antMatchers(HttpMethod.PUT, "/users/").permitAll()
                .antMatchers(HttpMethod.PUT, "/users/{username}").permitAll()
                .antMatchers(HttpMethod.PUT, "/users/changepassword/{username}").permitAll()
                
                .antMatchers(HttpMethod.POST, "/users/signup").permitAll()
                
                .antMatchers(HttpMethod.POST, "/users/login").permitAll()
                .antMatchers(HttpMethod.GET, "/users/login").permitAll()
                
                .antMatchers(HttpMethod.GET, "/users/{username}").permitAll()
                .antMatchers(HttpMethod.GET, "/users/id/{id}").permitAll()
                
                .antMatchers(HttpMethod.POST, "/users/checkOldPassword/{username}").permitAll()
                
                .antMatchers(HttpMethod.GET, "/users/whoami").permitAll()
                .antMatchers(HttpMethod.POST, "/users/whoami").permitAll()
                .antMatchers(HttpMethod.PUT, "/users/whoami").permitAll()
                .antMatchers(HttpMethod.PUT, "/users/whoami").permitAll()
                .antMatchers(HttpMethod.GET, "/reaction/").permitAll()
                .antMatchers(HttpMethod.POST, "/reaction/").permitAll()
                .antMatchers(HttpMethod.PUT, "/reaction/").permitAll()
                .antMatchers(HttpMethod.GET, "/reaction/{postId}").permitAll()
                .antMatchers(HttpMethod.GET, "/reaction/{voterId}/").permitAll()
                .antMatchers(HttpMethod.GET, "/reaction/voter/{voterId}/").permitAll()
                .antMatchers(HttpMethod.GET, "/reaction/voter/{voterId}").permitAll()
                .antMatchers(HttpMethod.POST, "/reaction/voter/{voterId}/").permitAll()
                .antMatchers(HttpMethod.GET, "/reaction/hasVoted/{voterId}").permitAll()
                
                .antMatchers(HttpMethod.POST, "/posts/").permitAll()
                .antMatchers(HttpMethod.GET, "/posts/").permitAll()
                .antMatchers(HttpMethod.GET, "/posts/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/posts/{name}/").permitAll()
                .antMatchers(HttpMethod.DELETE, "/posts/{name}/").permitAll()
                
                
                .antMatchers(HttpMethod.POST, "/community/").permitAll()
                .antMatchers(HttpMethod.GET, "/community/").permitAll()
                .antMatchers(HttpMethod.PUT, "/community/").permitAll()
                .antMatchers(HttpMethod.GET, "/community/{name}").permitAll()
                .antMatchers(HttpMethod.PUT, "/community/{name}").permitAll()

                .anyRequest().authenticated();

        httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }
}