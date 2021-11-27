package nursingManagement.controllers;

import nursingManagement.converters.ActToActDto;
import nursingManagement.dto.ActDto;
import nursingManagement.exceptions.ActNotFoundException;
import nursingManagement.exceptions.PatientNotFoundException;
import nursingManagement.persistence.model.Act;
import nursingManagement.persistence.model.Patient;
import nursingManagement.services.ActService;
import nursingManagement.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/patient")
public class ActController {

    PatientService patientService;

    ActService actService;

    ActToActDto actToActDto;

    @Autowired
    public void setPatientService(PatientService patientService){
        this.patientService = patientService;
    }

    @Autowired
    public void setActService(ActService actService){
        this.actService = actService;
    }

    @Autowired
    public void setActToActDto(ActToActDto actToActDto){
        this.actToActDto = actToActDto;
    }

    @RequestMapping(method = RequestMethod.GET, path = "{patientId}/act/{actId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ActDto> showAct(@PathVariable Integer patientId, @PathVariable Integer actId){

        Act act = actService.getActById(actId);

        if(act == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(actToActDto.convert(act),HttpStatus.OK);
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

        if(bindingResult.hasErrors() || act.getId() != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try{

            patientService.addAct(patientId,act);

            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (PatientNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (ActNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{patientId}/act/{actId}", produces = MediaType.APPLICATION_JSON_VALUE)
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

    @RequestMapping(method = RequestMethod.PUT, path = "{patientId}/act/{actId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ActDto> editAct(@PathVariable Integer patientId, @PathVariable Integer actId ,@Valid @RequestBody Act act, BindingResult bindingResult) throws PatientNotFoundException {

        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(act.getId() != null && act.getId() != actId){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {

            act.setId(actId);

            patientService.editAct(patientId, act);

            return new ResponseEntity<>(actToActDto.convert(act),HttpStatus.OK);

        } catch (PatientNotFoundException e){

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            
        } catch (ActNotFoundException e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
