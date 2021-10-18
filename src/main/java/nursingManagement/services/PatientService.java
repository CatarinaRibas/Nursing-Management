package nursingManagement.services;

import nursingManagement.persistence.model.Patient;

import java.util.List;

public interface PatientService {

    public Patient getPatientById(Integer id);

    public Patient addPatient(Patient patient);

    public void deletePatient(Integer id);

    public List<Patient> getAllPatients();

    public List<Patient> getPatientsFiltered(String option);

}
