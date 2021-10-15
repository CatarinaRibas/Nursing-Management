package nursingManagement.services;

import nursingManagement.exceptions.UserNotFoundException;
import nursingManagement.persistence.dao.UserDao;
import nursingManagement.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
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
