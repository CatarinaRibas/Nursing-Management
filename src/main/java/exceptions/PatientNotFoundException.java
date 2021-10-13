package exceptions;

import errors.ErrorMessage;

public class PatientNotFoundException extends NursingManagementException {


    public PatientNotFoundException() {
        super(ErrorMessage.PATIENT_NOT_FOUND);
    }
}
