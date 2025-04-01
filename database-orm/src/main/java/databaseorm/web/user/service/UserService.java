package databaseorm.web.user.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import databaseorm.domain.user.User;
import databaseorm.web.user.dto.SignupDto;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {

    User create(SignupDto request, LocalDateTime now);

    void delete(String loginId);

    User read(String loginId);

    List<User> list();

    Long count();

    UserDetails loadUserByLoginId(String username) throws UsernameNotFoundException;
}
