package tbd.emergenciapp.dao;
import org.springframework.http.ResponseEntity;
import tbd.emergenciapp.dto.VolunteerDTO;
import tbd.emergenciapp.model.Volunteer;

import java.util.List;

public interface VolunteerDAO {
    List<Volunteer> getAllVolunteers();
    Volunteer getVolunteerById(Integer id);
    ResponseEntity createVolunteer(VolunteerDTO volunteer);
    ResponseEntity updateVolunteer(Integer id, VolunteerDTO volunteer);
    ResponseEntity deleteVolunteer(Integer id);
}
