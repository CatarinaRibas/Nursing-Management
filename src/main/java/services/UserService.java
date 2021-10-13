package services;

import exceptions.UserNotFoundException;
import persistence.model.User;

public interface UserService {

    public User getUserByEmail(String userEmail);

    public boolean login(String email, String password) throws UserNotFoundException;

}
