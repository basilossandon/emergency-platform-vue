package tbd.emergenciapp.db1.dao;
import org.springframework.http.ResponseEntity;
import tbd.emergenciapp.db1.dto.Db1VolunteerDTO;
import tbd.emergenciapp.db1.model.Db1Volunteer;

import java.util.List;

public interface Db1VolunteerDAO {

    List<Db1Volunteer> getAllVolunteers1();
    Db1Volunteer getVolunteerById1(Integer id);
    ResponseEntity createVolunteer1(Db1VolunteerDTO volunteer);
    ResponseEntity updateVolunteer1(Integer id, Db1VolunteerDTO volunteer);
    ResponseEntity deleteVolunteer1(Integer id);
}
