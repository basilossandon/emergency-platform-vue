package tbd.emergenciapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tbd.emergenciapp.dao.TaskDAO;
import tbd.emergenciapp.dto.TaskDTO;
import tbd.emergenciapp.model.Task;
import tbd.emergenciapp.repository.TaskRepository;

import java.util.List;


@RestController
@Validated
@RequestMapping(path = "/tasks")
@CrossOrigin(origins = "*")
public class TaskController implements TaskDAO {
        @Autowired
        private TaskRepository taskRepository;

        @GetMapping("")
        @ResponseBody
        public List<Task> getAllTasks() {
            return taskRepository.findAll();
        }

        @GetMapping(value = "/{id}")
        @ResponseBody
        public Task getTaskById(@PathVariable Integer id) {
            return taskRepository.findTaskById(id);
        }

        @PostMapping(value = "")
        public @ResponseBody
        ResponseEntity createTask(@RequestBody TaskDTO task){
            Task createdTask = new Task();
            createdTask.setName(task.getName());
            createdTask.setCapacity(task.getCapacity());
            createdTask.setStatus(task.getStatus());
            createdTask.setVolunteer(task.getVolunteer());

            if (createdTask.getName() != null && createdTask.getCapacity() != null && createdTask.getStatus() != null){

                return new ResponseEntity<>(taskRepository.save(createdTask), HttpStatus.CREATED);
            }

            return new ResponseEntity<>("La tarea a crear no puede contener valores nulos.", HttpStatus.BAD_REQUEST);
        }

        @PutMapping(value = "/{id}")
        public @ResponseBody
        ResponseEntity updateTask(@PathVariable Integer id, @RequestBody TaskDTO task){
            Task taskToUpdate = taskRepository.findTaskById(id);
            if (taskToUpdate == null)
                return new ResponseEntity<>("La tarea a editar no se ha podido encontrar.", HttpStatus.BAD_REQUEST);

            taskToUpdate.setName(task.getName());
            taskToUpdate.setCapacity(task.getCapacity());
            taskToUpdate.setStatus(task.getStatus());
            taskToUpdate.setVolunteer(task.getVolunteer());

            if (taskToUpdate.getName() != null && taskToUpdate.getCapacity() != null && taskToUpdate.getStatus() != null){
                return new ResponseEntity<>(taskRepository.save(taskToUpdate),HttpStatus.CREATED);
            }

            return new ResponseEntity<>("Un valor no puede ser modificado por un valor nulo.", HttpStatus.BAD_REQUEST);
        }

        @DeleteMapping(value = "/{id}")
        public ResponseEntity deleteTask(@PathVariable Integer id) {

            Task taskToDelete = taskRepository.findTaskById(id);
            if(taskToDelete != null)
            {
                taskRepository.deleteById(id);
                return new ResponseEntity<>("Se ha borrado la tarea " + id + " exitosamente.", HttpStatus.OK);
            }
            else return new ResponseEntity<>("La tarea " + id + " no se encuentra.",HttpStatus.NOT_FOUND);
        }

    }
