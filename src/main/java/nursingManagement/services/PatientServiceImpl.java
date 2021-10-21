package nursingManagement.services;

import nursingManagement.exceptions.PatientNotFoundException;
import nursingManagement.exceptions.UserNotFoundException;
import nursingManagement.persistence.dao.PatientDao;
import nursingManagement.persistence.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private PatientDao patientDao;

    @Autowired
    public void setPatientDao(PatientDao patientDao){
        this.patientDao = patientDao;
    }

    @Override
    public Patient getPatientById(Integer id) {
        return patientDao.findById(id);
    }

    @Transactional
    @Override
    public Patient addPatient(Patient patient) {
        return patientDao.saveOrUpdate(patient);
    }

    @Transactional
    @Override
    public void deletePatient(Integer id) {
        patientDao.delete(id);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientDao.findAll();
    }

    @Override
    public List<Patient> getPatientsFiltered(String option) {

        List<Patient> allPatients = patientDao.findAll();

        List<Patient> patientsFiltered = new LinkedList<>();

        for (Patient patient : allPatients) {
            if (patient.getVillage().equals(option)) {
                patientsFiltered.add(patient);
            }
        }

        return patientsFiltered;
    }

}
