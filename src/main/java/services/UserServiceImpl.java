package services;

import errors.ErrorMessage;
import exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import persistence.dao.UserDao;
import persistence.model.User;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public User getUserByEmail(String userEmail) {
        return userDao.findByEmail(userEmail);
    }

    @Override
    public boolean login(String userEmail, String password) throws UserNotFoundException {

        User user = Optional.ofNullable(userDao.findByEmail(userEmail)).orElseThrow(UserNotFoundException::new);

        if(!user.getEmail().equals(userEmail)){
            throw new UserNotFoundException();
        }
        
        if(user.getPassword().equals(password)){
            return true;
        }
        return false;
    }


}
