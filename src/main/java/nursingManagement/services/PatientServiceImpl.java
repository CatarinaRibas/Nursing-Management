package nursingManagement.services;

import com.mysql.cj.jdbc.exceptions.SQLError;
import nursingManagement.exceptions.ActNotFoundException;
import nursingManagement.exceptions.PatientNotFoundException;
import nursingManagement.persistence.dao.ActDao;
import nursingManagement.persistence.dao.PatientDao;
import nursingManagement.persistence.model.Act;
import nursingManagement.persistence.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    private PatientDao patientDao;

    private ActDao actDao;

    @Autowired
    public void setPatientDao(PatientDao patientDao){
        this.patientDao = patientDao;
    }

    @Autowired
    public void setActDao(ActDao actDao){
        this.actDao = actDao;
    }

    @Override
    public Patient getPatientById(Integer id) {
        return patientDao.findById(id);
    }

    @Transactional
    @Override
    public Patient savePatient(Patient patient) {

        return patientDao.saveOrUpdate(patient);
    }

    @Transactional
    @Override
    public void deletePatient(Integer id) throws PatientNotFoundException {

        Patient patient = Optional.ofNullable(patientDao.findById(id)).orElseThrow(PatientNotFoundException::new);

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

    @Transactional
    public Act addAct(Integer patientId, Act act) throws PatientNotFoundException{

        Patient patient = Optional.ofNullable(patientDao.findById(patientId)).orElseThrow(PatientNotFoundException::new);

        patient.addAct(act);
        patientDao.saveOrUpdate(patient);

        return patient.getActs().get(patient.getActs().size() - 1);

    }

    @Transactional
    public void deleteAct(Integer patientId, Integer actId) throws PatientNotFoundException, ActNotFoundException {

        Patient patient = Optional.ofNullable(patientDao.findById(patientId)).orElseThrow(PatientNotFoundException::new);

        Act act = Optional.ofNullable(actDao.findById(actId)).orElseThrow(ActNotFoundException::new);

        if(!act.getPatient().getId().equals(patientId)){
            throw new ActNotFoundException();
        }

        patient.deleteAct(act);
        patientDao.saveOrUpdate(patient);




    }

}
