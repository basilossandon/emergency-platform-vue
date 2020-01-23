package tbd.emergenciapp.db1.dao;

import org.springframework.http.ResponseEntity;
import tbd.emergenciapp.db1.dto.Db1EmergencyDTO;
import tbd.emergenciapp.db1.model.Db1Emergency;


import java.util.List;

public interface Db1EmergencyDAO {
    List<Db1Emergency> getAllEmergencies1();
    Db1Emergency getEmergencyById1(Integer id);
    ResponseEntity createEmergency1(Db1EmergencyDTO emergency);
    ResponseEntity updateEmergency1(Integer id, Db1EmergencyDTO emergency);
    ResponseEntity deleteEmergency1(Integer id);
}
