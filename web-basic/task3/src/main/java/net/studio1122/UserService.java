package net.studio1122;

import java.time.LocalDateTime;

public class UserService {

    private final UserDAO userDAO;

    // 의존성 주입(DI)를 통해 UserDAO를 주입 받음
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User createUser(String username, String password) {
        User user = new User(username, password, LocalDateTime.now());
        return userDAO.save(user);
    }

    public User getUser(long id) {
        return userDAO.findById(id);
    }

    public long getUserSize() {
        return userDAO.count();
    }
}
