package tbd.emergenciapp.db2.dao;
import org.springframework.http.ResponseEntity;
import tbd.emergenciapp.db2.dto.Db2VolunteerDTO;
import tbd.emergenciapp.db2.model.Db2Volunteer;

import java.util.List;

public interface Db2VolunteerDAO {

    List<Db2Volunteer> getAllVolunteers2();
    Db2Volunteer getVolunteerById2(Integer id);
    ResponseEntity createVolunteer2(Db2VolunteerDTO volunteer);
    ResponseEntity updateVolunteer2(Integer id, Db2VolunteerDTO volunteer);
    ResponseEntity deleteVolunteer2(Integer id);
}
