package project.semi.securitytest.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import project.semi.securitytest.config.oauth.PrincipalOauth2UserService;

@Configuration
@EnableWebSecurity //스프링 시큐리티 필터가 필터 체틴에 등록
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) //secured 어노테이션 활성화, preAuthorize 어노테이션 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //소셜 로그인
    //1. 코드 받기(인증) 2. 액세스 토큰(사용자 정보에 접근할 권한) 3. 사용자 프로필 정보 가져옴 4. 정보를 토대로 회원가입 자동 진행
    //이메일, 전화번호, 이름, 아이디 제공
    //추가적인 구성이 필요하다면?(주소나 등급 설정 등...)

    private final PrincipalOauth2UserService principalOauth2UserService;

//    @Bean
//    public BCryptPasswordEncoder encodePwd() {
//        BCryptPasswordEncoder bCryptPasswordEncoder =  new BCryptPasswordEncoder();
//        this.principalOauth2UserService.setBCryptPasswordEncoder(bCryptPasswordEncoder);
//        return bCryptPasswordEncoder;
//    }


    @Override
    protected  void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/user/**").authenticated()
                .antMatchers("/manager/**").access("hasRole('ROLD_ADMIN') or hasRole('ROLE_MANAGER')")
                .antMatchers("/admin/**").access("hasRole('ROLD_ADMIN')")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/loginForm")
                .loginProcessingUrl("/login") //login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행
                .defaultSuccessUrl("/")
                .and()
                .oauth2Login()//구글 로그인이 완료된 후 후처리 필요, loadUser()에서 후처리 가능
                .loginPage("/loginForm")
                .userInfoEndpoint()
                .userService(principalOauth2UserService);



    }

}
