package tbd.emergenciapp.db2.dao;

import org.springframework.http.ResponseEntity;
import tbd.emergenciapp.db2.dto.Db2EmergencyDTO;
import tbd.emergenciapp.db2.model.Db2Emergency;

import java.util.List;

public interface Db2EmergencyDAO {
    List<Db2Emergency> getAllEmergencies2();
    Db2Emergency getEmergencyById2(Integer id);
    ResponseEntity createEmergency2(Db2EmergencyDTO emergency);
    ResponseEntity updateEmergency2(Integer id, Db2EmergencyDTO emergency);
    ResponseEntity deleteEmergency2(Integer id);
}
