package tbd.emergenciapp.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.multipart.MultipartFile;
import tbd.emergenciapp.dao.VolunteerDAO;
import tbd.emergenciapp.dto.VolunteerDTO;
import tbd.emergenciapp.model.Volunteer;
import tbd.emergenciapp.repository.VolunteerRepository;
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
public class VolunteerController implements VolunteerDAO{


    @PostMapping(value = "/upload", consumes = "text/csv")
    public void uploadSimple(@RequestBody InputStream body) throws IOException {
        volunteerRepository.saveAll(CsvUtils.read(Volunteer.class, body));
    }
    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public void uploadMultipart(@RequestParam("file") MultipartFile file) throws IOException {
        volunteerRepository.saveAll(CsvUtils.read(Volunteer.class, file.getInputStream()));
    }



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

    @ResponseBody
    @RequestMapping("/pages")
    public List<Volunteer> getAllVolunteersPageable(@PageableDefault(value=10, page=1)   Pageable pageable) {
        Page<Volunteer> page = volunteerRepository.findAll(pageable);
        return page.getContent();
    }

    @PostMapping(value = "")
    public @ResponseBody
    ResponseEntity createVolunteer(@RequestBody VolunteerDTO volunteer){
        Volunteer createdVolunteer = new Volunteer();
        createdVolunteer.setName(volunteer.getName());
        createdVolunteer.setSex(volunteer.getSex());
        createdVolunteer.setLastname(volunteer.getLastname());
        createdVolunteer.setEmail(volunteer.getEmail());
        createdVolunteer.setLatitude(volunteer.getLatitude());
        createdVolunteer.setLongitude(volunteer.getLongitude());


        if(createdVolunteer.getName() != null && createdVolunteer.getEmail()!=null){
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
        volunteerUpdate.setSex(volunteer.getSex());
        volunteerUpdate.setVolunteerDimensions(volunteer.getVolunteerDimensions());

        if(volunteerUpdate.getName() !=null && volunteerUpdate.getSex()!=null){
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
