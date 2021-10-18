package nursingManagement.controllers;

import nursingManagement.converters.PatientToPatientDto;
import nursingManagement.dto.PatientDto;
import nursingManagement.persistence.model.Patient;
import nursingManagement.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/patients")
public class PatientController {

    PatientService patientService;

    PatientToPatientDto patientToPatientDto;

    @Autowired
    public void setPatientService(PatientService patientService) {
        this.patientService = patientService;
    }

    @Autowired
    public void setPatientToPatientDto(PatientToPatientDto patientToPatientDto){
        this.patientToPatientDto = patientToPatientDto;
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/all","","/"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PatientDto>> listAllPatients(){

        List<PatientDto> patientDtosList = patientService.getAllPatients().stream()
                .map(patient -> patientToPatientDto.convert(patient))
                .collect(Collectors.toList());

        Collections.sort(patientDtosList, Comparator.comparing(PatientDto::getName));

        return new ResponseEntity<>(patientDtosList, HttpStatus.OK);
    }
}
