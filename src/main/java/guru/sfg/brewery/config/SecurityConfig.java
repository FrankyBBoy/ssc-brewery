package guru.sfg.brewery.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static org.springframework.http.HttpMethod.GET;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests(authorize -> {
          authorize
              .antMatchers("/", "/webjars/**", "/login",
                  "/resources/**", "/beers/find", "/beers*").permitAll()
              .antMatchers(GET, "/api/v1/beer/**").permitAll()
              .mvcMatchers(GET, "/api/v1/beerUpc/{upc}").permitAll();
        })
        .authorizeRequests()
        .anyRequest().authenticated().and()
        .formLogin().and()
        .httpBasic();
  }

}
