package project.semi.securitytest.config.auth;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import project.semi.securitytest.domain.entity.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

//시큐리티의 session 공간 Security ContextHolder
//Authentication 타입
//-> 안에 User 타입
//-> UserDetails 타입

//Security Session -> Authentication -> UserDetails

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrincipalDetails implements UserDetails, OAuth2User {

    private User user;

    public User getUser() {
        return user;
    }


    //OAuth2User 오버라이드 메소드
    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }




    //UserDeatils 오버라이드 메소드
    //user의 권한 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        //ex) 1년 동안 로그인을 하지 않으면 휴면계정으로 변환

        return true;
    }

    @Override
    public String getName() {
        return null;
    }
}
