package tbd.emergenciapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tbd.emergenciapp.model.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    Task findTaskById(Integer id);

    List<Task> findByEmergencyId(int EmergencyId);
}
