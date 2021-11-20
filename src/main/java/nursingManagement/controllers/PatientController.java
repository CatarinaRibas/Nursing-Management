package nursingManagement.controllers;

import nursingManagement.converters.ActToActDto;
import nursingManagement.converters.PatientDtoToPatient;
import nursingManagement.converters.PatientToPatientDto;
import nursingManagement.dto.ActDto;
import nursingManagement.dto.PatientDto;
import nursingManagement.exceptions.ActNotFoundException;
import nursingManagement.exceptions.PatientNotFoundException;
import nursingManagement.persistence.model.Act;
import nursingManagement.persistence.model.Patient;
import nursingManagement.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/patient")
public class PatientController {

    PatientService patientService;

    PatientToPatientDto patientToPatientDto;

    PatientDtoToPatient patientDtoToPatient;

    ActToActDto actToActDto;

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

    @Autowired
    public void setActToActDto(ActToActDto actToActDto){
        this.actToActDto = actToActDto;
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
    public ResponseEntity<?> addPatient(@Valid @RequestBody Patient patient, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {

            Patient savedPatient = patientService.savePatient(patient);

            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (Exception e){

          return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PatientDto> editPatient(@Valid @RequestBody Patient patient, BindingResult bindingResult, @PathVariable Integer id){

        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(patient.getId() != null && !patient.getId().equals(id)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(patientService.getPatientById(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {

            patient.setId(id);

            patientService.savePatient(patient);

            return new ResponseEntity<>(HttpStatus.OK);
            
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PatientDto> deletePatient(@PathVariable Integer id){

        try {
            patientService.deletePatient(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (PatientNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(method = RequestMethod.GET, path = "/{patientId}/act", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ActDto>> listPatientActs(@PathVariable Integer patientId){

        Patient patient = patientService.getPatientById(patientId);

        if(patient == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<ActDto> actsDto = patient.getActs().stream().map(act -> actToActDto.convert(act)).collect(Collectors.toList());

        return new ResponseEntity<>(actsDto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{patientId}/act", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addAct(@PathVariable Integer patientId, @Valid @RequestBody Act act, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try{

            patientService.addAct(patientId,act);

            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (PatientNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{patientId}/act/{actId}/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteAct(@PathVariable Integer patientId, @PathVariable Integer actId){

        try{

            patientService.deleteAct(patientId,actId);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (ActNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (PatientNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


}
