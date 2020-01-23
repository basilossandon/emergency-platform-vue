package tbd.emergenciapp.db2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tbd.emergenciapp.db1.model.Db1Task;
import tbd.emergenciapp.db2.model.Db2Task;


import java.util.List;

@Repository
public interface Db2TaskRepository extends JpaRepository<Db2Task, Integer> {
    Db2Task findTaskById(Integer id);

    List<Db2Task> findByEmergencyId(int EmergencyId);

    @Query(value = "SELECT 2 AS key, current_database() AS database_name ", nativeQuery=true)
    Db2Task getDb2TaskRepository();
}
