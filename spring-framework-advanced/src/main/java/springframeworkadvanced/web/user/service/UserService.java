package springframeworkadvanced.web.user.service;


import springframeworkadvanced.domain.user.User;
import springframeworkadvanced.web.user.dto.SignupDto;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {

    User create(SignupDto request, LocalDateTime now);

    void delete(String loginId);

    User read(String loginId);

    List<User> list();

    Long count();
}
