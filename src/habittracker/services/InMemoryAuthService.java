package habittracker.services;

import habittracker.models.User;

import java.util.HashMap;
import java.util.Map;

public class InMemoryAuthService implements AuthService {

    private final Map<String, User> usersByName = new HashMap<>();

    @Override
    public User register(String username, String email, String password) {
        if (usersByName.containsKey(username)) {
            throw new IllegalArgumentException("Username already exists.");
        }
        User u = new User(username, email, password);
        usersByName.put(username, u);
        return u;
    }

    @Override
    public User login(String username, String password) {
        User u = usersByName.get(username);
        if (u == null || !u.getPasswordHash().equals(password)) {
            throw new IllegalArgumentException("Invalid username or password.");
        }
        return u;
    }
}
