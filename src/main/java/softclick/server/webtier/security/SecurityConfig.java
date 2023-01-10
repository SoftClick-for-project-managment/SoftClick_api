package softclick.server.webtier.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import softclick.server.webtier.filters.AuthenticationFilter;
import softclick.server.webtier.filters.AuthorizationFilter;

import static org.springframework.http.HttpMethod.*;

@Configuration @EnableWebSecurity @RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String ROLE_DIRECTOR = "ROLE_DIRECTOR";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_EMPLOYEE = "ROLE_EMPLOYEE";
    public static final String ROLE_PROJECT_MANAGER = "ROLE_PROJECT_MANAGER";
    public static final String ROLE_TEAM_MANAGER = "ROLE_TEAM_MANAGER";

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // User and Auth Resources Protection
        http.authorizeRequests().antMatchers(POST, "/login").permitAll();
        http.authorizeRequests().antMatchers(POST, "/api/v1/users/**").permitAll();
        http.authorizeRequests().antMatchers(GET, "/api/v1/users/details").authenticated();
        http.authorizeRequests().antMatchers(GET, "/api/v1/users/**").hasAnyAuthority(ROLE_ADMIN);
        http.authorizeRequests().antMatchers(PUT, "/api/v1/users/**").authenticated();
        http.authorizeRequests().antMatchers(PATCH, "/api/v1/users/**").hasAnyAuthority(ROLE_EMPLOYEE, ROLE_ADMIN);
        http.authorizeRequests().antMatchers(DELETE, "/api/v1/users/**").hasAnyAuthority(ROLE_ADMIN);

        // Client Resources Protection
        http.authorizeRequests().antMatchers(GET, "/api/v1/clients/**").hasAnyAuthority(ROLE_DIRECTOR, ROLE_ADMIN);
        http.authorizeRequests().antMatchers(POST, "/api/v1/clients").hasAnyAuthority(ROLE_DIRECTOR,ROLE_ADMIN);
        http.authorizeRequests().antMatchers(DELETE, "/api/v1/clients/**").hasAnyAuthority(ROLE_ADMIN, ROLE_DIRECTOR);
        http.authorizeRequests().antMatchers(PUT, "/api/v1/clients/**").hasAnyAuthority(ROLE_ADMIN, ROLE_DIRECTOR);

        // invoice Resources Protection
        http.authorizeRequests().antMatchers(GET, "/api/v1/invoices/**").hasAnyAuthority(ROLE_DIRECTOR, ROLE_ADMIN);
        http.authorizeRequests().antMatchers(POST, "/api/v1/invoices").hasAnyAuthority(ROLE_DIRECTOR,ROLE_ADMIN);
        http.authorizeRequests().antMatchers(DELETE, "/api/v1/invoices/**").hasAnyAuthority(ROLE_ADMIN, ROLE_DIRECTOR);
        http.authorizeRequests().antMatchers(PUT, "/api/v1/invoices/**").hasAnyAuthority(ROLE_ADMIN, ROLE_DIRECTOR);
        // expenses Resources Protection
        http.authorizeRequests().antMatchers(GET, "/api/v1/expenses/**").hasAnyAuthority(ROLE_DIRECTOR, ROLE_ADMIN);
        http.authorizeRequests().antMatchers(POST, "/api/v1/expenses").hasAnyAuthority(ROLE_DIRECTOR,ROLE_ADMIN);
        http.authorizeRequests().antMatchers(DELETE, "/api/v1/expenses/**").hasAnyAuthority(ROLE_ADMIN, ROLE_DIRECTOR);
        http.authorizeRequests().antMatchers(PUT, "/api/v1/expenses/**").hasAnyAuthority(ROLE_ADMIN, ROLE_DIRECTOR);
        // Teams Resources Protection
        http.authorizeRequests().antMatchers(GET, "/api/v1/teams/**").hasAnyAuthority(ROLE_PROJECT_MANAGER,ROLE_TEAM_MANAGER,ROLE_DIRECTOR, ROLE_ADMIN);
        http.authorizeRequests().antMatchers(POST, "/api/v1/teams").hasAnyAuthority(ROLE_PROJECT_MANAGER,ROLE_TEAM_MANAGER,ROLE_DIRECTOR,ROLE_ADMIN);
        http.authorizeRequests().antMatchers(DELETE, "/api/v1/teams/**").hasAnyAuthority(ROLE_PROJECT_MANAGER,ROLE_ADMIN,ROLE_DIRECTOR, ROLE_TEAM_MANAGER);
        http.authorizeRequests().antMatchers(PUT, "/api/v1/teams/**").hasAnyAuthority(ROLE_PROJECT_MANAGER,ROLE_ADMIN,ROLE_DIRECTOR, ROLE_TEAM_MANAGER);
        // Task Resources Protection
        http.authorizeRequests().antMatchers(GET, "/api/v1/tasks").hasAnyAuthority(ROLE_EMPLOYEE, ROLE_ADMIN, ROLE_PROJECT_MANAGER, ROLE_DIRECTOR);
//        http.authorizeRequests().antMatchers(GET, "/api/v1/tasks/project/**").hasAnyAuthority();
        http.authorizeRequests().antMatchers(GET, "/api/v1/tasks/**").hasAnyAuthority(ROLE_EMPLOYEE, ROLE_ADMIN, ROLE_DIRECTOR, ROLE_PROJECT_MANAGER);
        http.authorizeRequests().antMatchers(POST, "/api/v1/tasks").hasAnyAuthority(ROLE_PROJECT_MANAGER, ROLE_DIRECTOR);
        http.authorizeRequests().antMatchers(DELETE, "/api/v1/tasks/**").hasAnyAuthority(ROLE_PROJECT_MANAGER, ROLE_DIRECTOR);
        http.authorizeRequests().antMatchers(PUT, "/api/v1/tasks/**").hasAnyAuthority(ROLE_PROJECT_MANAGER, ROLE_DIRECTOR);

        http.authorizeRequests().anyRequest().authenticated();
                    
        http.addFilter(new AuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new AuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
