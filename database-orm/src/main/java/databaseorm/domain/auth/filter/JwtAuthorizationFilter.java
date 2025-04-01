package databaseorm.domain.auth.filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import databaseorm.domain.common.UserRole;
import databaseorm.domain.common.utils.TokenUtils;
import databaseorm.domain.model.DetailsUser;
import databaseorm.domain.user.User;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        List<String> roleLessList = Arrays.asList("/signup");

        if (roleLessList.contains(request.getRequestURI())) {
            chain.doFilter(request, response);
            return;
        }

        Cookie tokenCookie = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equalsIgnoreCase("token")) {
                tokenCookie = cookie;
                break;
            }
        }

        try {
            if (tokenCookie != null && !tokenCookie.getValue().equalsIgnoreCase("")) {
                String token = tokenCookie.getValue();

                if (TokenUtils.isValidToken(token)) {

                    Claims claims = TokenUtils.getClaimsFromToken(token);

                    DetailsUser authentication = new DetailsUser();
                    User user = new User();
                    user.setLoginId(claims.get("loginId").toString());
                    user.setUserRole(UserRole.valueOf(claims.get("role").toString()));
                    authentication.setUser(user);

                    AbstractAuthenticationToken authenticationToken =
                            UsernamePasswordAuthenticationToken
                                    .authenticated(authentication, token, authentication.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                } else {
                    request.setAttribute("unauthorization", "401 Error while authentication");
                }
            } else {
                request.setAttribute("unauthorization", "401 No key or Key expiration");
            }
        } catch (Exception ignored) {}

        chain.doFilter(request, response);
    }
}
