package net.studio1122;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserDAO {

    private long sequence = 1L;
    private final Map<Long, User> userMap = new HashMap<>();

    public User findById(long id) {
        return userMap.get(id);
    }

    public User save(User user) {
        user.setId(sequence++);
        userMap.put(user.getId(), user);
        return user;
    }

    public long count() {
        return userMap.size();
    }
}
