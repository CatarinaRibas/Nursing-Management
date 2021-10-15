package nursingManagement.exceptions;

import nursingManagement.errors.ErrorMessage;

public class UserNotFoundException extends NursingManagementException {

    public UserNotFoundException() {
        super(ErrorMessage.USER_NOT_FOUND);
    }
}
