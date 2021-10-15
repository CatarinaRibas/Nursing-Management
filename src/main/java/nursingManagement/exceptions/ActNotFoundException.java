package nursingManagement.exceptions;

import nursingManagement.errors.ErrorMessage;

public class ActNotFoundException extends NursingManagementException {

    public ActNotFoundException() {
        super(ErrorMessage.ACT_NOT_FOUND);
    }
}
