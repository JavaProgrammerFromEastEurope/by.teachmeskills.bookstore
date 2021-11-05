package by.teachmeskills.bookstore.dao.config;

import by.teachmeskills.bookstore.dao.service.impl.UserSecurityService;
import by.teachmeskills.bookstore.dao.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public Environment env;

    @Autowired
    public UserSecurityService userSecurityService;


    private BCryptPasswordEncoder passwordEncoder() {
        return SecurityUtility.passwordEncoder();
    }

    private static final String[] PUBLIC_MATCHERS = {
            "/resources/static/**",
            "/resources/templates/**",
            "/resources/templates/common/**",
            "/resources/static/image/**",
            "/resources/static/image/book/**",
            "/resources/static/js/**",
            "/",
            "/new-user",
            "/login",
            "/forget-password",
            "/bookshelf",
            "/shopping-cart/add-item",
            "/book-detail/**",
            "/my-profile/**",
            "/hours",
            "/faq",
            "/search-by-category",
            "/search-book",
            "/error",
            "/by_teachmeskills_bookstore_war_exploded/",
            "/by_teachmeskills_bookstore_war_exploded/new-user",
            "/by_teachmeskills_bookstore_war_exploded/login",
            "/by_teachmeskills_bookstore_war_exploded/forget-password",
            "/by_teachmeskills_bookstore_war_exploded/bookshelf",
            "/by_teachmeskills_bookstore_war_exploded/shopping-cart/add-item",
            "/by_teachmeskills_bookstore_war_exploded/book-detail/**",
            "/by_teachmeskills_bookstore_war_exploded/my-profile/**",
            "/by_teachmeskills_bookstore_war_exploded/hours",
            "/by_teachmeskills_bookstore_war_exploded/faq",
            "/by_teachmeskills_bookstore_war_exploded/search-by-category",
            "/by_teachmeskills_bookstore_war_exploded/search-book",
            "/by_teachmeskills_bookstore_war_exploded/error",
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(PUBLIC_MATCHERS)
                .permitAll()
                .anyRequest()
                .authenticated();

        http
                .csrf()
                .disable()
                .cors()
                .disable()
                .formLogin().failureUrl("/login?error")
                /*.defaultSuccessUrl("/")*/
                .loginPage("/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/?logout").deleteCookies("remember-me").permitAll()
                .and()
                .rememberMe();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
    }
}