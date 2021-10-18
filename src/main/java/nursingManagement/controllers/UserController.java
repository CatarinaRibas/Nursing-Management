package nursingManagement.controllers;

import nursingManagement.converters.UserToUserDto;
import nursingManagement.dto.UserDto;
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
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

    private UserService userService;

    private UserToUserDto userToUserDto;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @Autowired
    public void setUserToUserDto(UserToUserDto userToUserDto){
        this.userToUserDto = userToUserDto;
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/signin"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> signin(@RequestBody User user,BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            Boolean singin = userService.login(user.getEmail(),user.getPassword());

            if(!singin){
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            User loginUser = userService.getUserByEmail(user.getEmail());

            return new ResponseEntity<>(userToUserDto.convert(loginUser),HttpStatus.OK);

        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


}
