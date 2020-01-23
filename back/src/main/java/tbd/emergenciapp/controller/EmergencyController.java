package tbd.emergenciapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tbd.emergenciapp.db1.dao.Db1EmergencyDAO;
import tbd.emergenciapp.db1.dto.Db1EmergencyDTO;
import tbd.emergenciapp.db1.model.Db1Emergency;
import tbd.emergenciapp.db1.repository.Db1EmergencyRepository;
import tbd.emergenciapp.db2.dto.Db2EmergencyDTO;
import tbd.emergenciapp.db2.model.Db2Emergency;
import tbd.emergenciapp.db2.repository.Db2EmergencyRepository;
import tbd.emergenciapp.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/emergencies")
@CrossOrigin("*")
public class EmergencyController {
    @Autowired
    Db1EmergencyRepository db1EmergencyRepository;

    @Autowired
    Db2EmergencyRepository db2EmergencyRepository;

    @GetMapping("")
    @ResponseBody
    public List<Db1Emergency> getAllEmergencies1() {
        return db1EmergencyRepository.findAll();
    }

    @GetMapping("")
    @ResponseBody
    public List<Db2Emergency> getAllEmergencies2() {
        return db2EmergencyRepository.findAll();
    }

    @GetMapping(value = "db1/{id}")
    @ResponseBody
    public Db1Emergency getEmergencyById1(@PathVariable Integer id) {
        Optional<Db1Emergency> optEmergency = Optional.ofNullable(db1EmergencyRepository.findEmergencyById(id));
        if(optEmergency.isPresent()) {
            return optEmergency.get();
        }else {
            throw new NotFoundException("Student not found with id " + id);
        }
    }

    @GetMapping(value = "db2/{id}")
    @ResponseBody
    public Db2Emergency getEmergencyById2(@PathVariable Integer id) {
        Optional<Db2Emergency> optEmergency = Optional.ofNullable(db2EmergencyRepository.findEmergencyById(id));
        if(optEmergency.isPresent()) {
            return optEmergency.get();
        }else {
            throw new NotFoundException("Student not found with id " + id);
        }
    }

    @PostMapping(value = "")
    public @ResponseBody
    ResponseEntity createEmergency2(@RequestBody Db2EmergencyDTO emergency){
        Db2Emergency createdEmergency = new Db2Emergency();
        createdEmergency.setName(emergency.getName());
        createdEmergency.setLocation(emergency.getLocation());
        createdEmergency.setStatus(emergency.getStatus());
        createdEmergency.setLatitude(emergency.getLatitude());
        createdEmergency.setLongitude(emergency.getLongitude());
        createdEmergency.setDescription(emergency.getDescription());

        if (createdEmergency.getName() != null &&
            createdEmergency.getLocation() != null &&
            createdEmergency.getStatus() != null){

            return new ResponseEntity<>(db2EmergencyRepository.save(createdEmergency), HttpStatus.CREATED);
        }

        return new ResponseEntity<>("La emergencia a crear no puede contener valores nulos.", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "")
    public @ResponseBody
    ResponseEntity createEmergency1(@RequestBody Db1EmergencyDTO emergency){
        Db1Emergency createdEmergency = new Db1Emergency();
        createdEmergency.setName(emergency.getName());
        createdEmergency.setLocation(emergency.getLocation());
        createdEmergency.setStatus(emergency.getStatus());
        createdEmergency.setLatitude(emergency.getLatitude());
        createdEmergency.setLongitude(emergency.getLongitude());
        createdEmergency.setDescription(emergency.getDescription());

        if (createdEmergency.getName() != null &&
                createdEmergency.getLocation() != null &&
                createdEmergency.getStatus() != null){

            return new ResponseEntity<>(db1EmergencyRepository.save(createdEmergency), HttpStatus.CREATED);
        }

        return new ResponseEntity<>("La emergencia a crear no puede contener valores nulos.", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "db1/{id}")
    public @ResponseBody
    ResponseEntity updateEmergency1(@PathVariable Integer id, @RequestBody Db1EmergencyDTO emergency){
        Db1Emergency emergencyToUpdate = db1EmergencyRepository.findEmergencyById(id);
        if (emergencyToUpdate == null)
            return new ResponseEntity<>("La emergencia a editar no se ha podido encontrar.", HttpStatus.BAD_REQUEST);

        emergencyToUpdate.setName(emergency.getName());
        emergencyToUpdate.setLocation(emergency.getLocation());
        emergencyToUpdate.setStatus(emergency.getStatus());

        if (emergencyToUpdate.getName() != null && emergencyToUpdate.getLocation() != null && emergencyToUpdate.getStatus() != null){
            return new ResponseEntity<>(db1EmergencyRepository.save(emergencyToUpdate),HttpStatus.CREATED);
        }

        return new ResponseEntity<>("Un valor no puede ser modificado por un valor nulo.", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "db2/{id}")
    public @ResponseBody
    ResponseEntity updateEmergency2(@PathVariable Integer id, @RequestBody Db2EmergencyDTO emergency){
        Db2Emergency emergencyToUpdate = db2EmergencyRepository.findEmergencyById(id);
        if (emergencyToUpdate == null)
            return new ResponseEntity<>("La emergencia a editar no se ha podido encontrar.", HttpStatus.BAD_REQUEST);

        emergencyToUpdate.setName(emergency.getName());
        emergencyToUpdate.setLocation(emergency.getLocation());
        emergencyToUpdate.setStatus(emergency.getStatus());

        if (emergencyToUpdate.getName() != null && emergencyToUpdate.getLocation() != null && emergencyToUpdate.getStatus() != null){
            return new ResponseEntity<>(db2EmergencyRepository.save(emergencyToUpdate),HttpStatus.CREATED);
        }

        return new ResponseEntity<>("Un valor no puede ser modificado por un valor nulo.", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "db1/{id}")
    public  @ResponseBody
    ResponseEntity deleteEmergency1(@PathVariable Integer id) {

        Db1Emergency emergencyToDelete = db1EmergencyRepository.findEmergencyById(id);
        if(emergencyToDelete != null)
        {
            db1EmergencyRepository.deleteById(id);
            return new ResponseEntity<>("Se ha borrado la emergencia " + id + " exitosamente.", HttpStatus.OK);
        }
        else return new ResponseEntity<>("El emergencia " + id + " no se encuentra.",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "db2/{id}")
    public  @ResponseBody
    ResponseEntity deleteEmergency2(@PathVariable Integer id) {

        Db2Emergency emergencyToDelete = db2EmergencyRepository.findEmergencyById(id);
        if(emergencyToDelete != null)
        {
            db2EmergencyRepository.deleteById(id);
            return new ResponseEntity<>("Se ha borrado la emergencia " + id + " exitosamente.", HttpStatus.OK);
        }
        else return new ResponseEntity<>("El emergencia " + id + " no se encuentra.",HttpStatus.NOT_FOUND);
    }

}
