package tbd.emergenciapp.dao;

import org.springframework.http.ResponseEntity;
import tbd.emergenciapp.dto.DimensionDTO;
import tbd.emergenciapp.model.Dimension;

import java.util.List;

public interface DimensionDAO {
    List<Dimension> getAllDimensions();
    Dimension getDimensionById(Integer id);
    ResponseEntity createDimension(DimensionDTO dimension);
    ResponseEntity updateDimension(Integer id, DimensionDTO dimension);
    ResponseEntity deleteDimension(Integer id);
}
