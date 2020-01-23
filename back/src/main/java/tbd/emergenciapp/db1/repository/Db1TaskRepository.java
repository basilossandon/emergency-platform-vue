package tbd.emergenciapp.db1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tbd.emergenciapp.db1.model.Db1Emergency;
import tbd.emergenciapp.db1.model.Db1Task;


import java.util.List;

@Repository
public interface Db1TaskRepository extends JpaRepository<Db1Task, Integer> {
    Db1Task findTaskById(Integer id);

    List<Db1Task> findByEmergencyId(int EmergencyId);

    @Query(value = "SELECT 1 AS key, current_database() AS database_name ", nativeQuery=true)
    Db1Task getDb2TaskRepository();


}
