package nursingManagement.controllers;

import nursingManagement.converters.ActToActDto;
import nursingManagement.dto.ActDto;
import nursingManagement.persistence.model.Act;
import nursingManagement.services.ActService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/act")
public class ActController {

    ActService actService;

    ActToActDto actToActDto;

    @Autowired
    public void setActService(ActService actService){
        this.actService = actService;
    }

    @Autowired
    public void setActToActDto(ActToActDto actToActDto){
        this.actToActDto = actToActDto;
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{actId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ActDto> editAct(@Valid @RequestBody Act act, BindingResult bindingResult, @PathVariable Integer actId){

        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(act.getId() != null && !act.getId().equals(actId)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(actService.getActById(actId) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        act.setId(actId);

        actService.saveAct(act);

        return new ResponseEntity<>(actToActDto.convert(act),HttpStatus.OK);

    }






}
