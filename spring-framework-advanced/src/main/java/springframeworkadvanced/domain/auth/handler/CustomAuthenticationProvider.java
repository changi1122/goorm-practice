package springframeworkadvanced.domain.auth.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springframeworkadvanced.domain.model.DetailsUser;
import springframeworkadvanced.web.user.service.UserService;

public class CustomAuthenticationProvider implements AuthenticationProvider  {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken loginToken = (UsernamePasswordAuthenticationToken) authentication;

        String loginId = loginToken.getName();

        String password = (String) loginToken.getCredentials();
        DetailsUser detailsUser = (DetailsUser) userService.loadUserByLoginId(loginId);

        if(!passwordEncoder.matches(password, detailsUser.getPassword())) {
            throw new BadCredentialsException(password + "는 틀린 비밀번호입니다.");
        }
        return new UsernamePasswordAuthenticationToken(detailsUser, password, detailsUser.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
