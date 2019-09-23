package tbd.emergenciapp.controller;


import tbd.emergenciapp.dao.VolunteerDAO;
import tbd.emergenciapp.dto.VolunteerDTO;
import tbd.emergenciapp.model.Volunteer;
import tbd.emergenciapp.repository.VolunteerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping(path = "/volunteers")
@CrossOrigin(origins = "*")
public class VolunteerController implements VolunteerDAO{
    @Autowired
    private VolunteerRepository volunteerRepository;

    @GetMapping("")
    @ResponseBody
    public List<Volunteer> getAllVolunteers(){
        return volunteerRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public Volunteer getVolunteerById(@PathVariable Integer id){
        return volunteerRepository.findVolunteerById(id);
    }

    @PostMapping(value = "")
    public @ResponseBody
    ResponseEntity createVolunteer(@RequestBody VolunteerDTO volunteer){
        Volunteer createdVolunteer = new Volunteer();
        createdVolunteer.setName(volunteer.getName());
        createdVolunteer.setAge(volunteer.getAge());
        createdVolunteer.setSex(volunteer.getSex());
        createdVolunteer.setDimension(volunteer.getDimension());

        if(createdVolunteer.getName() != null && createdVolunteer.getAge() !=null && createdVolunteer.getSex()!=null){
            return new ResponseEntity<>(volunteerRepository.save(createdVolunteer), HttpStatus.CREATED);
        }

        return new ResponseEntity<>("EL usuario a crear no puede contener valores nulos.", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/{id}")
    public @ResponseBody
    ResponseEntity updateVolunteer(@PathVariable Integer id, @RequestBody VolunteerDTO volunteer){
        Volunteer volunteerUpdate = volunteerRepository.findVolunteerById(id);
        if(volunteerUpdate == null){
            return new ResponseEntity<>("El voluntario a editar no se ha podido encontrar. ", HttpStatus.BAD_REQUEST);
        }
        volunteerUpdate.setName(volunteer.getName());
        volunteerUpdate.setAge(volunteer.getAge());
        volunteerUpdate.setSex(volunteer.getSex());
        volunteerUpdate.setDimension(volunteer.getDimension());

        if(volunteerUpdate.getName() !=null && volunteerUpdate.getAge()!=null && volunteerUpdate.getSex()!=null){
            return new ResponseEntity<>(volunteerRepository.save(volunteerUpdate), HttpStatus.CREATED);
        }

        return  new ResponseEntity<>("Un valor no puede ser modificado por un valor nulo.", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{id}")
    public @ResponseBody
    ResponseEntity deleteVolunteer(@PathVariable Integer id){
        Volunteer volunteerDelete = volunteerRepository.findVolunteerById(id);

        if(volunteerDelete!=null){
            volunteerRepository.deleteById(id);
            return new ResponseEntity<>("Borrado exitosamente",HttpStatus.OK);
        }

        return new ResponseEntity("El usuario a borrar no existe. ", HttpStatus.BAD_REQUEST);
    }
}
