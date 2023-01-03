package hexlet.code.app.config;

import hexlet.code.app.service.UserService;
import hexlet.code.app.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;

import static org.springframework.http.HttpMethod.POST;

//@Configuration
//@EnableWebSecurity
public class IntroAppConfig
//        extends WebSecurityConfigurerAdapter
{
//    @Autowired
//    UserDetailsServiceImpl userService;

//    @Bean
//    public UserService userService() {
//        return new UserServiceImpl();
//    }

    // Указываем, что для сравнения хешей паролей
    // будет использоваться кодировщик BCrypt
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http
//                .authorizeRequests()
//                .antMatchers("/welcome").permitAll()
//                .antMatchers(POST, "/api/users").permitAll()
//                .antMatchers( "/api/*").permitAll()
//                .antMatchers( "/h2console/*").permitAll()
//                .antMatchers( "/h2console/*").permitAll()
//                .anyRequest().authenticated()
//                .and().httpBasic();
//    }
//
//
////     Указываем, что будем использовать созданный нами диспетчер аутентификации вместо дефолтного
//    @Override
//    public void configure(AuthenticationManagerBuilder builder) throws Exception {
//        builder.userDetailsService(userService);
//    }
}
