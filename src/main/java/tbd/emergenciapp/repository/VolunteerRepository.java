package tbd.emergenciapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tbd.emergenciapp.model.Volunteer;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Integer> {
    Volunteer findVolunteerById(Integer id);
    void  saveAll(List<Volunteer>, volunteers);
}
