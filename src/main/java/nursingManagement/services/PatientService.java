package nursingManagement.services;

import nursingManagement.exceptions.ActNotFoundException;
import nursingManagement.exceptions.PatientNotFoundException;
import nursingManagement.persistence.model.Act;
import nursingManagement.persistence.model.Patient;
import java.util.List;

public interface PatientService {

    public Patient getPatientById(Integer id);

    public Patient savePatient(Patient patient);

    public void deletePatient(Integer id) throws PatientNotFoundException;

    public List<Patient> getAllPatients();

    public List<Patient> getPatientsFiltered(String option);

    public Act addAct(Integer patientId, Act act) throws PatientNotFoundException, ActNotFoundException;

    public Act editAct(Integer patientId, Act act) throws PatientNotFoundException, ActNotFoundException;

    void deleteAct(Integer patientId, Integer actId) throws PatientNotFoundException, ActNotFoundException;
}
