package tbd.emergenciapp.db1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tbd.emergenciapp.db1.model.Db1Emergency;


@Repository
public interface Db1EmergencyRepository extends JpaRepository<Db1Emergency, Integer>{
    Db1Emergency findEmergencyById(Integer id);

    @Query(value = "SELECT 1 AS key, current_database() AS database_name ", nativeQuery=true)
    Db1Emergency getDb2EmergencyRepository();
}
