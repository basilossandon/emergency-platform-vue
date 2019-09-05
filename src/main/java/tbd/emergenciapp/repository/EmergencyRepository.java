package tbd.emergenciapp.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import tbd.emergenciapp.model.Emergency;

@Repository
public interface EmergencyRepository extends JpaRepository<Emergency, Integer>{
    Emergency findEmergencyById(Integer id);
}
