package nursingManagement.services;

import nursingManagement.exceptions.UserNotFoundException;
import nursingManagement.persistence.model.User;

public interface UserService {

    public User getUserByEmail(String userEmail);

    public boolean login(String email, String password) throws UserNotFoundException;

}
