package nursingManagement.controllers;

import nursingManagement.errors.ErrorMessage;
import nursingManagement.exceptions.UserNotFoundException;
import nursingManagement.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import nursingManagement.services.UserService;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/signin"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> signin(@RequestBody User user, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Boolean singin = null;
        try {
            singin = userService.login(user.getEmail(),user.getPassword());
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        if(!singin){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(userService.getUserByEmail(user.getEmail()).toString(),HttpStatus.OK);

    }


}
