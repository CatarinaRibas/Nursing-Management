package nursingManagement.services;

import com.mysql.cj.jdbc.exceptions.SQLError;
import nursingManagement.exceptions.ActNotFoundException;
import nursingManagement.exceptions.PatientNotFoundException;
import nursingManagement.persistence.model.Act;
import nursingManagement.persistence.model.Patient;
import org.springframework.dao.DataIntegrityViolationException;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface PatientService {

    public Patient getPatientById(Integer id);

    public Patient savePatient(Patient patient);

    public void deletePatient(Integer id) throws PatientNotFoundException;

    public List<Patient> getAllPatients();

    public List<Patient> getPatientsFiltered(String option);

    public Act addAct(Integer patientId, Act act) throws PatientNotFoundException;

    void deleteAct(Integer patientId, Integer actId) throws PatientNotFoundException, ActNotFoundException;
}
