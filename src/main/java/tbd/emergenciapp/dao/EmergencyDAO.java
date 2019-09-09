package tbd.emergenciapp.dao;

import org.springframework.http.ResponseEntity;
import tbd.emergenciapp.dto.EmergencyDTO;
import tbd.emergenciapp.model.Emergency;

import java.util.List;

public interface EmergencyDAO {
    List<Emergency> getAllEmergencies();
    Emergency getEmergencyById(Integer id);
    ResponseEntity createEmergency(EmergencyDTO emergency);
    ResponseEntity updateEmergency(Integer id, EmergencyDTO emergency);
    ResponseEntity deleteEmergency(Integer id);
}
