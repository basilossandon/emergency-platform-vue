package tbd.emergenciapp.db2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tbd.emergenciapp.db2.model.Db2Emergency;


@Repository
public interface Db2EmergencyRepository extends JpaRepository<Db2Emergency, Integer>{
    Db2Emergency findEmergencyById(Integer id);

    @Query(value = "SELECT 2 AS key, current_database() AS database_name ", nativeQuery=true)
    Db2Emergency getDb2EmergencyRepository();
}
