package tbd.emergenciapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tbd.emergenciapp.dao.EmergencyDAO;
import tbd.emergenciapp.dto.EmergencyDTO;
import tbd.emergenciapp.model.Emergency;
import tbd.emergenciapp.exception.NotFoundException;
import tbd.emergenciapp.repository.EmergencyRepository;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/emergencies")
@CrossOrigin("*")
public class EmergencyController implements EmergencyDAO {
    @Autowired
    private EmergencyRepository emergencyRepository;

    @GetMapping("")
    @ResponseBody
    public List<Emergency> getAllEmergencies() {
        return emergencyRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public Emergency getEmergencyById(@PathVariable Integer id) {
        Optional<Emergency> optEmergency = Optional.ofNullable(emergencyRepository.findEmergencyById(id));
        if(optEmergency.isPresent()) {
            return optEmergency.get();
        }else {
            throw new NotFoundException("Student not found with id " + id);
        }
    }

    @PostMapping(value = "")
    public @ResponseBody
    ResponseEntity createEmergency(@RequestBody EmergencyDTO emergency){
        Emergency createdEmergency = new Emergency();
        createdEmergency.setName(emergency.getName());
        createdEmergency.setLocation(emergency.getLocation());
        createdEmergency.setStatus(emergency.getStatus());
        createdEmergency.setLatitude(emergency.getLatitude());
        createdEmergency.setLongitude(emergency.getLongitude());
        createdEmergency.setDescription(emergency.getDescription());

        if (createdEmergency.getName() != null &&
            createdEmergency.getLocation() != null &&
            createdEmergency.getStatus() != null){

            return new ResponseEntity<>(emergencyRepository.save(createdEmergency), HttpStatus.CREATED);
        }

        return new ResponseEntity<>("La emergencia a crear no puede contener valores nulos.", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/{id}")
    public @ResponseBody
    ResponseEntity updateEmergency(@PathVariable Integer id, @RequestBody EmergencyDTO emergency){
        Emergency emergencyToUpdate = emergencyRepository.findEmergencyById(id);
        if (emergencyToUpdate == null)
            return new ResponseEntity<>("La emergencia a editar no se ha podido encontrar.", HttpStatus.BAD_REQUEST);

        emergencyToUpdate.setName(emergency.getName());
        emergencyToUpdate.setLocation(emergency.getLocation());
        emergencyToUpdate.setStatus(emergency.getStatus());

        if (emergencyToUpdate.getName() != null && emergencyToUpdate.getLocation() != null && emergencyToUpdate.getStatus() != null){
            return new ResponseEntity<>(emergencyRepository.save(emergencyToUpdate),HttpStatus.CREATED);
        }

        return new ResponseEntity<>("Un valor no puede ser modificado por un valor nulo.", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{id}")
    public  @ResponseBody
    ResponseEntity deleteEmergency(@PathVariable Integer id) {

        Emergency emergencyToDelete = emergencyRepository.findEmergencyById(id);
        if(emergencyToDelete != null)
        {
            emergencyRepository.deleteById(id);
            return new ResponseEntity<>("Se ha borrado la emergencia " + id + " exitosamente.", HttpStatus.OK);
        }
        else return new ResponseEntity<>("El emergencia " + id + " no se encuentra.",HttpStatus.NOT_FOUND);
    }

}
