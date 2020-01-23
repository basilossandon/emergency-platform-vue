package tbd.emergenciapp.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.multipart.MultipartFile;

import tbd.emergenciapp.db1.dto.Db1VolunteerDTO;
import tbd.emergenciapp.db1.model.Db1Volunteer;
import tbd.emergenciapp.db1.repository.Db1VolunteerRepository;
import tbd.emergenciapp.db2.dto.Db2VolunteerDTO;
import tbd.emergenciapp.db2.model.Db2Volunteer;
import tbd.emergenciapp.db2.repository.Db2VolunteerRepository;

import tbd.emergenciapp.utilities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@Validated
@RequestMapping(path = "/volunteers")
@CrossOrigin(origins = "*")
public class VolunteerController {

    @Autowired
    Db1VolunteerRepository db1VolunteerRepository;

    @Autowired
    Db2VolunteerRepository db2VolunteerRepository;

    @PostMapping(value = "db1/upload", consumes = "text/csv")
    public void uploadSimple1(@RequestBody InputStream body) throws IOException {
        db1VolunteerRepository.saveAll(CsvUtils.read(Db1Volunteer.class, body));
    }
    @PostMapping(value = "db2/upload", consumes = "text/csv")
    public void uploadSimple2(@RequestBody InputStream body) throws IOException {
        db2VolunteerRepository.saveAll(CsvUtils.read(Db2Volunteer.class, body));
    }

    @PostMapping(value = "db1/upload", consumes = "multipart/form-data")
    public void uploadMultipart1(@RequestParam("file") MultipartFile file) throws IOException {
        db1VolunteerRepository.saveAll(CsvUtils.read(Db1Volunteer.class, file.getInputStream()));
    }

    @PostMapping(value = "db2/upload", consumes = "multipart/form-data")
    public void uploadMultipart2(@RequestParam("file") MultipartFile file) throws IOException {
        db2VolunteerRepository.saveAll(CsvUtils.read(Db2Volunteer.class, file.getInputStream()));
    }

    @GetMapping("")
    @ResponseBody
    public List<Db1Volunteer> getAllVolunteers1(){
        return db1VolunteerRepository.findAll();
    }

    @GetMapping("")
    @ResponseBody
    public List<Db2Volunteer> getAllVolunteers2(){
        return db2VolunteerRepository.findAll();
    }

    @GetMapping(value = "db1/{id}")
    @ResponseBody
    public Db1Volunteer getVolunteerById1(@PathVariable Integer id){
        return db1VolunteerRepository.findVolunteerById(id);
    }

    @GetMapping(value = "db2/{id}")
    @ResponseBody
    public Db2Volunteer getVolunteerById(@PathVariable Integer id){
        return db2VolunteerRepository.findVolunteerById(id);
    }


    @ResponseBody
    @RequestMapping("db1/pages")
    public List<Db1Volunteer> getAllVolunteersPageable1(@PageableDefault(value=10, page=1)   Pageable pageable) {
        Page<Db1Volunteer> page = db1VolunteerRepository.findAll(pageable);
        return page.getContent();
    }

    @ResponseBody
    @RequestMapping("db2/pages")
    public List<Db2Volunteer> getAllVolunteersPageable2(@PageableDefault(value=10, page=1)   Pageable pageable) {
        Page<Db2Volunteer> page = db2VolunteerRepository.findAll(pageable);
        return page.getContent();
    }

    @PostMapping(value = "")
    public @ResponseBody
    ResponseEntity createVolunteer(@RequestBody Db1VolunteerDTO volunteer){
        Db1Volunteer createdVolunteer = new Db1Volunteer();
        createdVolunteer.setName(volunteer.getName());
        createdVolunteer.setSex(volunteer.getSex());
        createdVolunteer.setLastname(volunteer.getLastname());
        createdVolunteer.setEmail(volunteer.getEmail());
        createdVolunteer.setLatitude(volunteer.getLatitude());
        createdVolunteer.setLongitude(volunteer.getLongitude());
        createdVolunteer.setRut(volunteer.getRut());
        createdVolunteer.setStrength(volunteer.getStrength());
        createdVolunteer.setDextery(volunteer.getDextery());
        createdVolunteer.setKnowledge(volunteer.getKnowledge());
        createdVolunteer.setMotivation(volunteer.getMotivation());
        createdVolunteer.setLeadership(volunteer.getLeadership());


        if(createdVolunteer.getName() != null && createdVolunteer.getEmail()!=null){
            return new ResponseEntity<>(db1VolunteerRepository.save(createdVolunteer), HttpStatus.CREATED);
        }

        return new ResponseEntity<>("EL usuario a crear no puede contener valores nulos.", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "")
    public @ResponseBody
    ResponseEntity createVolunteer(@RequestBody Db2VolunteerDTO volunteer){
        Db2Volunteer createdVolunteer = new Db2Volunteer();
        createdVolunteer.setName(volunteer.getName());
        createdVolunteer.setSex(volunteer.getSex());
        createdVolunteer.setLastname(volunteer.getLastname());
        createdVolunteer.setEmail(volunteer.getEmail());
        createdVolunteer.setLatitude(volunteer.getLatitude());
        createdVolunteer.setLongitude(volunteer.getLongitude());
        createdVolunteer.setRut(volunteer.getRut());
        createdVolunteer.setStrength(volunteer.getStrength());
        createdVolunteer.setDextery(volunteer.getDextery());
        createdVolunteer.setKnowledge(volunteer.getKnowledge());
        createdVolunteer.setMotivation(volunteer.getMotivation());
        createdVolunteer.setLeadership(volunteer.getLeadership());


        if(createdVolunteer.getName() != null && createdVolunteer.getEmail()!=null){
            return new ResponseEntity<>(db2VolunteerRepository.save(createdVolunteer), HttpStatus.CREATED);
        }

        return new ResponseEntity<>("EL usuario a crear no puede contener valores nulos.", HttpStatus.BAD_REQUEST);
    }



    @PutMapping(value = "db1/{id}")
    public @ResponseBody
    ResponseEntity updateVolunteer(@PathVariable Integer id, @RequestBody Db1VolunteerDTO volunteer){
        Db1Volunteer volunteerUpdate = db1VolunteerRepository.findVolunteerById(id);
        if(volunteerUpdate == null){
            return new ResponseEntity<>("El voluntario a editar no se ha podido encontrar. ", HttpStatus.BAD_REQUEST);
        }
        volunteerUpdate.setName(volunteer.getName());
        volunteerUpdate.setEmail(volunteer.getEmail());
        volunteerUpdate.setSex(volunteer.getSex());


        if(volunteerUpdate.getName() !=null && volunteerUpdate.getSex()!=null){
            return new ResponseEntity<>(db1VolunteerRepository.save(volunteerUpdate), HttpStatus.CREATED);
        }

        return  new ResponseEntity<>("Un valor no puede ser modificado por un valor nulo.", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "db2/{id}")
    public @ResponseBody
    ResponseEntity updateVolunteer(@PathVariable Integer id, @RequestBody Db2VolunteerDTO volunteer){
        Db2Volunteer volunteerUpdate = db2VolunteerRepository.findVolunteerById(id);
        if(volunteerUpdate == null){
            return new ResponseEntity<>("El voluntario a editar no se ha podido encontrar. ", HttpStatus.BAD_REQUEST);
        }
        volunteerUpdate.setName(volunteer.getName());
        volunteerUpdate.setEmail(volunteer.getEmail());
        volunteerUpdate.setSex(volunteer.getSex());


        if(volunteerUpdate.getName() !=null && volunteerUpdate.getSex()!=null){
            return new ResponseEntity<>(db2VolunteerRepository.save(volunteerUpdate), HttpStatus.CREATED);
        }

        return  new ResponseEntity<>("Un valor no puede ser modificado por un valor nulo.", HttpStatus.BAD_REQUEST);
    }


    //Prueba de postgis


    //      //      //

    @DeleteMapping(value = "db1/{id}")
    public @ResponseBody
    ResponseEntity deleteVolunteer1(@PathVariable Integer id){
        Db1Volunteer volunteerDelete = db1VolunteerRepository.findVolunteerById(id);

        if(volunteerDelete!=null){
            db1VolunteerRepository.deleteById(id);
            return new ResponseEntity<>("Borrado exitosamente",HttpStatus.OK);
        }

        return new ResponseEntity("El usuario a borrar no existe. ", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "db2/{id}")
    public @ResponseBody
    ResponseEntity deleteVolunteer2(@PathVariable Integer id){
        Db2Volunteer volunteerDelete = db2VolunteerRepository.findVolunteerById(id);

        if(volunteerDelete!=null){
            db2VolunteerRepository.deleteById(id);
            return new ResponseEntity<>("Borrado exitosamente",HttpStatus.OK);
        }

        return new ResponseEntity("El usuario a borrar no existe. ", HttpStatus.BAD_REQUEST);
    }
}
