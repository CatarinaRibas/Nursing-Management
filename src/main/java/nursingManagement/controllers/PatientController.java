package nursingManagement.controllers;

import nursingManagement.converters.PatientDtoToPatient;
import nursingManagement.converters.PatientToPatientDto;
import nursingManagement.dto.PatientDto;
import nursingManagement.exceptions.PatientNotFoundException;
import nursingManagement.exceptions.UserNotFoundException;
import nursingManagement.persistence.model.Patient;
import nursingManagement.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import javax.validation.Valid;
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

    PatientDtoToPatient patientDtoToPatient;

    @Autowired
    public void setPatientService(PatientService patientService) {
        this.patientService = patientService;
    }

    @Autowired
    public void setPatientToPatientDto(PatientToPatientDto patientToPatientDto){
        this.patientToPatientDto = patientToPatientDto;
    }

    @Autowired
    public void setPatientDtoToPatient(PatientDtoToPatient patientDtoToPatient){
        this.patientDtoToPatient = patientDtoToPatient;
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/all","","/"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PatientDto>> listAllPatients(@RequestParam(value = "village", required = false, defaultValue = "all") String village) {

        if (!village.equals("all")) {
                List<PatientDto> patientDtoListFiltered = patientService.getPatientsFiltered(village).stream()
                        .map(patient -> patientToPatientDto.convert(patient))
                        .collect(Collectors.toList());

                Collections.sort(patientDtoListFiltered, Comparator.comparing(PatientDto::getName));

                return new ResponseEntity<>(patientDtoListFiltered, HttpStatus.OK);
        }

        List<PatientDto> patientDtosList = patientService.getAllPatients().stream()
                .map(patient -> patientToPatientDto.convert(patient))
                .collect(Collectors.toList());

        Collections.sort(patientDtosList, Comparator.comparing(PatientDto::getName));

        return new ResponseEntity<>(patientDtosList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PatientDto> showPatient(@PathVariable Integer id){

        Patient patient = patientService.getPatientById(id);

        if(patient == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(patientToPatientDto.convert(patient),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addPatient(@Valid @RequestBody PatientDto patientDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Patient savedPatient = patientService.addPatient(patientDtoToPatient.convert(patientDto));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
