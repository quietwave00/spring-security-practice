package project.semi.securitytest.config.oauth;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    //구글로부터 받은 userRequest에 대한 후처리 메소드
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("userRequest: " + userRequest);
        //userRequest: org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest@6209b8ba
        System.out.println("userRequest: " + userRequest.getClientRegistration());
        //userRequest: ClientRegistration{registrationId='google', clientId='490961154864-i1dilml42mtdffdu2h1kh6eilaam3a56.apps.googleusercontent.com', clientSecret='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', clientAuthenticationMethod=org.springframework.security.oauth2.core.ClientAuthenticationMethod@4fcef9d3, authorizationGrantType=org.springframework.security.oauth2.core.AuthorizationGrantType@5da5e9f3, redirectUri='{baseUrl}/{action}/oauth2/code/{registrationId}', scopes=[email, profile], providerDetails=org.springframework.security.oauth2.client.registration.ClientRegistration$ProviderDetails@136a39bd, clientName='Google'}
        System.out.println("userRequest: " + userRequest.getAccessToken());
        //userRequest: org.springframework.security.oauth2.core.OAuth2AccessToken@3c9c8532
        System.out.println("userRequest: " + userRequest.getAccessToken().getTokenValue());
        //userRequest: ya29.a0AVvZVsossUs5XuYnWEBxqcwKJPkq0YnXBGkpA0GuArFvIdmNbVLJ42VY3NSHyNq_jp8Yjm43uOLxNy7vr3SfayVfO43ib-IPBsENMdyv_by8rSe3Jk3MZNkHt5_w7exSMsW5rj_Q_yVHmemzNCKzVKykpBDuaCgYKAbcSARISFQGbdwaIDgOQa228FINetUI0UddOsw0163
        System.out.println("userRequest: " + super.loadUser(userRequest).getAttributes());
        //userRequest: {sub=100590799182526385026, name=갸갸, given_name=갸, family_name=갸, picture=https://lh3.googleusercontent.com/a/AGNmyxZoByKpU8TrWC_ghAKFhsFE781m93CtP9zwzCpb=s96-c, email=quietwave00@gmail.com, email_verified=true, locale=ko}


        return super.loadUser(userRequest);
    }
}
