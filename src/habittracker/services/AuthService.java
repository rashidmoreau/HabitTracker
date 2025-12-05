package habittracker.services;

import habittracker.models.User;

public interface AuthService {
    User register(String username, String email, String password) throws IllegalArgumentException;
    User login(String username, String password) throws IllegalArgumentException;
}
