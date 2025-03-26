package springframeworkadvanced.web.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springframeworkadvanced.domain.user.User;
import springframeworkadvanced.domain.user.UserRepository;
import springframeworkadvanced.web.user.dto.SignupDto;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(SignupDto request, LocalDateTime now) {
        User user = new User();
        user.setLoginId(request.getLoginId());
        user.setPassword(request.getPassword());
        user.setUsername(request.getUsername());
        user.setCreatedAt(now);
        return userRepository.save(user);
    }

    @Override
    public void delete(String loginId) {
        userRepository.deleteByLoginId(loginId);
    }

    @Override
    public User read(String loginId) {
        return userRepository.findByLoginId(loginId).orElseThrow();
    }

    @Override
    public List<User> list() {
        return userRepository.findAll();
    }

    @Override
    public Long count() {
        return userRepository.count();
    }
}
