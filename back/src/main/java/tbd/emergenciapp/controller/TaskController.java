package tbd.emergenciapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import tbd.emergenciapp.db1.dto.Db1TaskDTO;
import tbd.emergenciapp.db1.model.Db1Task;
import tbd.emergenciapp.db1.repository.Db1EmergencyRepository;
import tbd.emergenciapp.db1.repository.Db1TaskRepository;
import tbd.emergenciapp.db2.dto.Db2TaskDTO;
import tbd.emergenciapp.db2.model.Db2Task;
import tbd.emergenciapp.db2.repository.Db2EmergencyRepository;
import tbd.emergenciapp.db2.repository.Db2TaskRepository;

import tbd.emergenciapp.exception.NotFoundException;


import java.util.List;


@RestController
@Validated
@RequestMapping(path = "/tasks")
@CrossOrigin("*")
public class TaskController {
    @Autowired
    private Db1TaskRepository db1TaskRepository;

    @Autowired
    private Db2TaskRepository db2TaskRepository;

    @Autowired
    private Db1EmergencyRepository db1EmergencyRepository;

    @Autowired
    private Db2EmergencyRepository db2EmergencyRepository;

    @GetMapping("db1/emergencies/{emergencyId}")
    public List<Db1Task> getContactByEmergencyId1(@PathVariable int emergencyId) {

        if(!db1EmergencyRepository.existsById(emergencyId)) {
            throw new NotFoundException("Emergency not found!");
        }

        return db1TaskRepository.findByEmergencyId(emergencyId);
    }

    @GetMapping("db2/emergencies/{emergencyId}")
    public List<Db2Task> getContactByEmergencyId2(@PathVariable int emergencyId) {

        if(!db2EmergencyRepository.existsById(emergencyId)) {
            throw new NotFoundException("Emergency not found!");
        }

        return db2TaskRepository.findByEmergencyId(emergencyId);
    }

    @GetMapping("")
    @ResponseBody
    public List<Db1Task> getAllTasks1() {
        return db1TaskRepository.findAll();
    }

    @GetMapping("")
    @ResponseBody
    public List<Db2Task> getAllTasks2() {
        return db2TaskRepository.findAll();
    }


    @GetMapping(value = "db1/{id}")
    @ResponseBody
    public Db1Task getTaskById1(@PathVariable Integer id) {
        return db1TaskRepository.findTaskById(id);
    }

    @GetMapping(value = "db2/{id}")
    @ResponseBody
    public Db2Task getTaskById(@PathVariable Integer id) {
        return db2TaskRepository.findTaskById(id);
    }

    @PostMapping(value = "")
    public @ResponseBody
    ResponseEntity createTask1(@RequestBody Db1TaskDTO task){
        Db1Task createdTask1 = new Db1Task();
        createdTask1.setName(task.getName());
        createdTask1.setCapacity(task.getCapacity());
        createdTask1.setStatus(task.getStatus());

        if (createdTask1.getName() != null && createdTask1.getCapacity() != null && createdTask1.getStatus() != null){

            return new ResponseEntity<>(db1TaskRepository.save(createdTask1), HttpStatus.CREATED);
        }

        return new ResponseEntity<>("La tarea a crear no puede contener valores nulos.", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "")
    public @ResponseBody
    ResponseEntity createTask2(@RequestBody Db2TaskDTO task){
        Db2Task createdTask2 = new Db2Task();
        createdTask2.setName(task.getName());
        createdTask2.setCapacity(task.getCapacity());
        createdTask2.setStatus(task.getStatus());

        if (createdTask2.getName() != null && createdTask2.getCapacity() != null && createdTask2.getStatus() != null){

            return new ResponseEntity<>(db2TaskRepository.save(createdTask2), HttpStatus.CREATED);
        }

        return new ResponseEntity<>("La tarea a crear no puede contener valores nulos.", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("db1/emergencies/{emergencyId}")
    public Db1Task addTask1(@PathVariable int emergencyId,
                        @RequestBody Db1Task task) {
        return db1EmergencyRepository.findById(emergencyId)
                .map(emergency -> {
                    task.setEmergency(emergency);
                    return db1TaskRepository.save(task);
                }).orElseThrow(() -> new NotFoundException("emergency not found!"));
    }

    @PostMapping("db2/emergencies/{emergencyId}")
    public Db2Task addTask2(@PathVariable int emergencyId,
                        @RequestBody Db2Task task) {
        return db2EmergencyRepository.findById(emergencyId)
                .map(emergency -> {
                    task.setEmergency(emergency);
                    return db2TaskRepository.save(task);
                }).orElseThrow(() -> new NotFoundException("emergency not found!"));
    }

    @PutMapping(value = "db1/{id}")
    public @ResponseBody
    ResponseEntity updateTask1(@PathVariable Integer id, @RequestBody Db1TaskDTO task){
        Db1Task taskToUpdate1 = db1TaskRepository.findTaskById(id);
        if (taskToUpdate1 == null)
            return new ResponseEntity<>("La tarea a editar no se ha podido encontrar.", HttpStatus.BAD_REQUEST);

        taskToUpdate1.setName(task.getName());
        taskToUpdate1.setCapacity(task.getCapacity());
        taskToUpdate1.setStatus(task.getStatus());
        if (taskToUpdate1.getName() != null && taskToUpdate1.getCapacity() != null && taskToUpdate1.getStatus() != null){
            return new ResponseEntity<>(db1TaskRepository.save(taskToUpdate1),HttpStatus.CREATED);
        }

        return new ResponseEntity<>("Un valor no puede ser modificado por un valor nulo.", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "db2/{id}")
    public @ResponseBody
    ResponseEntity updateTask2(@PathVariable Integer id, @RequestBody Db2TaskDTO task){
        Db2Task taskToUpdate = db2TaskRepository.findTaskById(id);
        if (taskToUpdate == null)
            return new ResponseEntity<>("La tarea a editar no se ha podido encontrar.", HttpStatus.BAD_REQUEST);

        taskToUpdate.setName(task.getName());
        taskToUpdate.setCapacity(task.getCapacity());
        taskToUpdate.setStatus(task.getStatus());
        if (taskToUpdate.getName() != null && taskToUpdate.getCapacity() != null && taskToUpdate.getStatus() != null){
            return new ResponseEntity<>(db2TaskRepository.save(taskToUpdate),HttpStatus.CREATED);
        }

        return new ResponseEntity<>("Un valor no puede ser modificado por un valor nulo.", HttpStatus.BAD_REQUEST);
    }


    @DeleteMapping(value = "db1/{id}")
    public ResponseEntity deleteTask1(@PathVariable Integer id) {

        Db1Task taskToDelete = db1TaskRepository.findTaskById(id);
        if(taskToDelete != null)
        {
            db1TaskRepository.deleteById(id);
            return new ResponseEntity<>("Se ha borrado la tarea " + id + " exitosamente.", HttpStatus.OK);
        }
        else return new ResponseEntity<>("La tarea " + id + " no se encuentra.",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "db2/{id}")
    public ResponseEntity deleteTask(@PathVariable Integer id) {

        Db2Task taskToDelete = db2TaskRepository.findTaskById(id);
        if(taskToDelete != null)
        {
            db2TaskRepository.deleteById(id);
            return new ResponseEntity<>("Se ha borrado la tarea " + id + " exitosamente.", HttpStatus.OK);
        }
        else return new ResponseEntity<>("La tarea " + id + " no se encuentra.",HttpStatus.NOT_FOUND);
    }

}
