package nursingManagement.converters;

import nursingManagement.dto.PatientDto;
import nursingManagement.persistence.model.Patient;
import nursingManagement.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class PatientDtoToPatient implements Converter<PatientDto, Patient> {

    private PatientService patientService;

    @Autowired
    public void setPatientService(PatientService patientService) {
        this.patientService = patientService;
    }

    @Override
    public Patient convert(PatientDto patientDto) {

        Patient patient = (patientDto.getId()!= null ? patientService.getPatientById(patientDto.getId()): new Patient());

        patient.setName(patientDto.getName());
        patient.setBirthdate(patientDto.getBirthdate());
        patient.setAddress(patientDto.getAddress());
        patient.setVillage(patientDto.getVillage());
        patient.setPhone(patientDto.getPhone());
        patient.setEmail(patientDto.getEmail());

        return patient;
    }
}
