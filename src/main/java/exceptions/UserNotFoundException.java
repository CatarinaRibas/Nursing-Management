package exceptions;

import errors.ErrorMessage;

public class UserNotFoundException extends NursingManagementException {

    public UserNotFoundException() {
        super(ErrorMessage.USER_NOT_FOUND);
    }
}
