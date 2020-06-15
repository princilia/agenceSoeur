package techno.be.agencesoeur.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import techno.be.agencesoeur.service.AuthUserDetailService;
import techno.be.agencesoeur.service.UserService;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfigAdapter extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }


    AuthUserDetailService authUserDetailService;

    JwtTokenProvider jwtTokenProvider;
    @Autowired
    public SecurityConfigAdapter(AuthUserDetailService authUserDetailService, JwtTokenProvider jwtTokenProvider){
        this.jwtTokenProvider =jwtTokenProvider;
        this.authUserDetailService =authUserDetailService;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // TODO Auto-generated method stub
        // Mise en mémoire des utilisateurs du système
//		auth.inMemoryAuthentication()
//			.withUser("greg").password(passwordEncoder().encode("1234")).roles("ADMIN", "USER")
//			.and()
//			.withUser("gui").password(passwordEncoder().encode("1234")).roles("USER");
        auth.userDetailsService(authUserDetailService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .exceptionHandling()
                .and()
                .formLogin()
                .disable()
                .authorizeRequests()
                .antMatchers("/Api/user").hasRole("ADMIN")
                .antMatchers("/auth/signin","/js/**",
                        "/css/**",
                        "/img/**").permitAll()
                // ...
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .headers().frameOptions().disable() // pour avoir accès à H2
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider))
                .and()
                .httpBasic()
                .disable();
    }
}
