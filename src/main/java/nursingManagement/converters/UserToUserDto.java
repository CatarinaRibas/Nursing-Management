package nursingManagement.converters;

import nursingManagement.dto.UserDto;
import nursingManagement.persistence.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDto extends AbstractConverter<User, UserDto> {


    @Override
    public UserDto convert(User user) {

        UserDto userDto = new UserDto();

        userDto.setName(user.getName());

        return userDto;
    }
}
