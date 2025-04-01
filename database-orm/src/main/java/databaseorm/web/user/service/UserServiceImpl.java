package databaseorm.web.user.service;

import databaseorm.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import databaseorm.domain.common.UserRole;
import databaseorm.domain.model.DetailsUser;
import databaseorm.domain.user.User;
import databaseorm.web.user.dto.SignupDto;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User create(SignupDto request, LocalDateTime now) {
        User user = new User();
        user.setLoginId(request.getLoginId());
        user.setPassword(request.getPassword());
        user.setUsername(request.getUsername());
        user.setUserRole(UserRole.USER);
        user.setCreatedAt(now);
        return userRepository.save(user);
    }

    @Override
    @Transactional
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

    /**
     * description. 로그인 요청 시 사용자의 loginId를 받아 DB에서 사용자 정보를 가져오는 메소드
     *
     * @param loginId the username identifying the user whose data is required.
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByLoginId(String loginId) throws UsernameNotFoundException {

        if(loginId == null || loginId.equals("")) {
            throw new AuthenticationServiceException(loginId + " is Empty!");
        } else {
            return userRepository.findByLoginId(loginId)
                    .map(data -> new DetailsUser(Optional.of(data)))
                    .orElseThrow(() -> new AuthenticationServiceException(loginId));
        }
    }
}
