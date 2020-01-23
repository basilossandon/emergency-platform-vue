package tbd.emergenciapp.db1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tbd.emergenciapp.db1.model.Db1Emergency;
import tbd.emergenciapp.db1.model.Db1Volunteer;


@Repository
public interface Db1VolunteerRepository extends JpaRepository<Db1Volunteer, Integer> {
    Db1Volunteer findVolunteerById(Integer id);

    @Query(value = "SELECT 1 AS key, current_database() AS database_name ", nativeQuery=true)
    Db1Volunteer getDb2VolunteerRepository();
}
