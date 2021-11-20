package nursingManagement.services;

import nursingManagement.exceptions.ActNotFoundException;
import nursingManagement.exceptions.PatientNotFoundException;
import nursingManagement.persistence.dao.ActDao;
import nursingManagement.persistence.dao.PatientDao;
import nursingManagement.persistence.model.Act;
import nursingManagement.persistence.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ActServiceImpl implements ActService {

    private ActDao actDao;

    private PatientDao patientDao;

    @Autowired
    public void setActDao(ActDao actDao){
        this.actDao = actDao;
    }

    @Autowired
    public void setPatientDao(PatientDao patientDao){
        this.patientDao = patientDao;
    }

    @Override
    public Act getActById(Integer id) {
        return actDao.findById(id);
    }

    @Override
    @Transactional
    public Act saveAct(Act act) {
        return actDao.saveOrUpdate(act);
    }

    @Override
    @Transactional
    public void deleteAct(Integer id) throws ActNotFoundException {
        Act act = Optional.ofNullable(actDao.findById(id)).orElseThrow(ActNotFoundException::new);

        actDao.delete(id);
    }

    @Override
    public List<Act> getAllActs() {
        return actDao.findAll();
    }

}
