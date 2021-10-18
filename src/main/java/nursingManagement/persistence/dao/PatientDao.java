package nursingManagement.persistence.dao;

import nursingManagement.persistence.model.Patient;
import org.springframework.stereotype.Repository;

@Repository
public class PatientDao extends  GenericDao<Patient> {

    public PatientDao(){
        super(Patient.class);
    }
}
