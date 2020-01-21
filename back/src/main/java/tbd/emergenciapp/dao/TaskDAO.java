package tbd.emergenciapp.dao;

import org.springframework.http.ResponseEntity;
import tbd.emergenciapp.dto.TaskDTO;
import tbd.emergenciapp.model.Task;
import java.util.List;

public interface TaskDAO {
    List<Task> getAllTasks();
    Task getTaskById(Integer id);
    ResponseEntity createTask(TaskDTO task);
    ResponseEntity updateTask(Integer id, TaskDTO task);
    ResponseEntity deleteTask(Integer id);
}
