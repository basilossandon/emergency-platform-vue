package tbd.emergenciapp.db2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tbd.emergenciapp.db2.model.Db2Task;
import tbd.emergenciapp.db2.model.Db2Volunteer;


@Repository
public interface Db2VolunteerRepository extends JpaRepository<Db2Volunteer, Integer> {
    Db2Volunteer findVolunteerById(Integer id);

    @Query(value = "SELECT 2 AS key, current_database() AS database_name ", nativeQuery=true)
    Db2Task getDb2TaskRepository();
}
