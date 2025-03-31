package springframeworkadvanced.domain.user;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(User user);
    Optional<User> findByLoginId(String loginId);
    void deleteByLoginId(String loginId);
    List<User> findAll();
    long count();
}
