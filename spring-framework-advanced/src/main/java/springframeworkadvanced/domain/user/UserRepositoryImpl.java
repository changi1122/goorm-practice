package springframeworkadvanced.domain.user;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private Map<String, User> userMap = new HashMap<>();

    @Override
    public User save(User user) {
        userMap.put(user.getLoginId(), user);
        return user;
    }

    @Override
    public Optional<User> findByLoginId(String loginId) {
        User user = userMap.get(loginId);
        return Optional.ofNullable(user);
    }

    @Override
    public void deleteByLoginId(String loginId) {
        userMap.remove(loginId);
    }

    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        userMap.forEach((k, v) -> {
            result.add(v);
        });
        Collections.reverse(result);
        return result;
    }

    @Override
    public long count() {
        return userMap.size();
    }
}
