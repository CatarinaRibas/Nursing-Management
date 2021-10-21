package nursingManagement.converters;

import nursingManagement.dto.PatientDto;
import nursingManagement.services.AgeCalculator;
import nursingManagement.persistence.model.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientToPatientDto extends AbstractConverter<Patient, PatientDto> {

    @Override
    public PatientDto convert(Patient patient) {

        PatientDto patientDto = new PatientDto();

        patientDto.setName(patient.getName());
        patientDto.setBirthdate(patient.getBirthdate());
        patientDto.setAge(AgeCalculator.calculateAge(patient.getBirthdate()));
        patientDto.setAddress(patient.getAddress());
        patientDto.setVillage(patient.getVillage());
        patientDto.setPhone(patient.getPhone());
        patientDto.setEmail(patient.getEmail());

        return patientDto;
    }
}
