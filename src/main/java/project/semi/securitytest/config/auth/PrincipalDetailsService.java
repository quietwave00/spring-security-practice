package project.semi.securitytest.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.semi.securitytest.domain.entity.User;
import project.semi.securitytest.repository.UserRepository;

//security 설정에서 loginProcessingUrl("/login")
//login 요청->UserDetailsService 타입으로 IoC 되어 있는 loadUserByUsername 함수 실행

//시큐리티 세션(Authentication(UserDetails))
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepository.findByUsername(username);
        if(userEntity != null) {
            return new PrincipalDetails(userEntity);
        }
        return null;
    }
}
