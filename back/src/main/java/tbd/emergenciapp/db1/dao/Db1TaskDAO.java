package tbd.emergenciapp.db1.dao;

import org.springframework.http.ResponseEntity;
import tbd.emergenciapp.db1.dto.Db1TaskDTO;
import tbd.emergenciapp.db1.model.Db1Task;


import java.util.List;

public interface Db1TaskDAO {
    List<Db1Task> getAllTasks1();
    Db1Task getTaskById1(Integer id);
    ResponseEntity createTask1(Db1TaskDTO task);
    ResponseEntity updateTask1(Integer id, Db1TaskDTO task);
    ResponseEntity deleteTask1(Integer id);
}
