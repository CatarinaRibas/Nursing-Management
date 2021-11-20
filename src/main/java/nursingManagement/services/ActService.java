package nursingManagement.services;

import nursingManagement.exceptions.ActNotFoundException;
import nursingManagement.exceptions.PatientNotFoundException;
import nursingManagement.persistence.model.Act;
import nursingManagement.persistence.model.Patient;

import java.util.List;

public interface ActService {

    public Act getActById(Integer id);

    public Act saveAct(Act act);

    public void deleteAct(Integer id) throws ActNotFoundException;

    public List<Act> getAllActs();

}
