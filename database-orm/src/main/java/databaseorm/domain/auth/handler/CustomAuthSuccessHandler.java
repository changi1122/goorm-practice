package databaseorm.domain.auth.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import databaseorm.domain.common.AuthConstants;
import databaseorm.domain.common.utils.TokenUtils;
import databaseorm.domain.model.DetailsUser;
import databaseorm.domain.user.User;

import java.io.IOException;

public class CustomAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

        User user = ((DetailsUser) authentication.getPrincipal()).getUser();
        String token = TokenUtils.generateJwtToken(user);

        response.addHeader(AuthConstants.AUTH_HEADER, AuthConstants.TOKEN_TYPE + " " + token);

        // 쿠키 추가
        Cookie tokenCookie = createTokenCookie(token, TokenUtils.getTokenValidateTime());
        response.addCookie(tokenCookie);



        response.sendRedirect("/");  // 원하는 경로로 변경하세요
    }

    private Cookie createTokenCookie(String token, Long age) {
        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(age.intValue());
        cookie.setPath("/");
        return cookie;
    }
}
