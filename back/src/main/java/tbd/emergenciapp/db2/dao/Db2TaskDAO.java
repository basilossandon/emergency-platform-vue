package tbd.emergenciapp.db2.dao;

import org.springframework.http.ResponseEntity;
import tbd.emergenciapp.db2.dto.Db2TaskDTO;
import tbd.emergenciapp.db2.model.Db2Task;


import java.util.List;

public interface Db2TaskDAO {
    List<Db2Task> getAllTasks2();
    Db2Task getTaskById2(Integer id);
    ResponseEntity createTask2(Db2TaskDTO task);
    ResponseEntity updateTask2(Integer id, Db2TaskDTO task);
    ResponseEntity deleteTask2(Integer id);
}
