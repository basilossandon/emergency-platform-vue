package tbd.emergenciapp.controller;


import tbd.emergenciapp.db1.dao.Db1AppUserDAO;
import tbd.emergenciapp.db1.dto.Db1AppUserDTO;
import tbd.emergenciapp.db1.model.Db1AppUser;
import tbd.emergenciapp.db1.repository.Db1AppUserRepository;
import tbd.emergenciapp.db2.dao.Db2AppUserDAO;
import tbd.emergenciapp.db2.dto.Db2AppUserDTO;
import tbd.emergenciapp.db2.model.Db2AppUser;
import tbd.emergenciapp.db2.repository.Db2AppUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@Validated
@RequestMapping(path = "/users")
@CrossOrigin(origins = "*")
public class AppUserController{
    @Autowired
    Db1AppUserRepository db1AppUserRepository;

    @Autowired
    Db2AppUserRepository db2AppUserRepository;

    @GetMapping("")
    @ResponseBody
    public List<Db1AppUser> getAllAppUsers1() {
        return db1AppUserRepository.findAll();
    }

    @GetMapping("")
    @ResponseBody
    public  List<Db2AppUser> getAllAppUsers2() {
        return db2AppUserRepository.findAll();
    }

    @GetMapping(value = "db1/{id}")
    @ResponseBody
    public Db1AppUser getAppUserById1(@PathVariable Integer id) {
        return db1AppUserRepository.findAppUserById(id);
    }

    @GetMapping(value = "db2/{id}")
    @ResponseBody
    public Db2AppUser getAppUserById2(@PathVariable Integer id) {
        return db2AppUserRepository.findAppUserById(id);
    }


    @GetMapping(value = "db1/email/{email}")
    @ResponseBody
    public Db1AppUser getAppUserByEmail11(@PathVariable String email) {
        return db1AppUserRepository.findAppUserByEmail(email);
    }

    @GetMapping(value = "db2/email/{email}")
    @ResponseBody
    public Db2AppUser getAppUserByEmail2(@PathVariable String email) {
        return db2AppUserRepository.findAppUserByEmail(email);
    }

    @PostMapping(value = "db1/register")
    public @ResponseBody
    ResponseEntity createAppUser1(@RequestBody Db1AppUserDTO app_user){
        Db1AppUser createdAppUser = new Db1AppUser();
        createdAppUser.setName(app_user.getName());
        createdAppUser.setPassword(app_user.getPassword());
        createdAppUser.setEmail(app_user.getEmail());
        createdAppUser.setRole(app_user.getRole());
        if (createdAppUser.getName() != null && createdAppUser.getPassword() != null && createdAppUser.getEmail() != null && createdAppUser.getRole() != null){
            return new ResponseEntity<>(db1AppUserRepository.save(createdAppUser),HttpStatus.CREATED);
        }
        return new ResponseEntity<>("El usuario a crear no puede contener valores nulos.", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "db2/register")
    public @ResponseBody
    ResponseEntity createAppUser2(@RequestBody Db2AppUserDTO app_user){
        Db2AppUser createdAppUser = new Db2AppUser();
        createdAppUser.setName(app_user.getName());
        createdAppUser.setPassword(app_user.getPassword());
        createdAppUser.setEmail(app_user.getEmail());
        createdAppUser.setRole(app_user.getRole());
        if (createdAppUser.getName() != null && createdAppUser.getPassword() != null && createdAppUser.getEmail() != null && createdAppUser.getRole() != null){
            return new ResponseEntity<>(db2AppUserRepository.save(createdAppUser),HttpStatus.CREATED);
        }
        return new ResponseEntity<>("El usuario a crear no puede contener valores nulos.", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "db1/{id}")
    public @ResponseBody
    ResponseEntity updateAppUser1(@PathVariable Integer id, @RequestBody Db1AppUserDTO app_user){
        Db1AppUser appUserToUpdate = db1AppUserRepository.findAppUserById(id);
        if (appUserToUpdate == null)
            return new ResponseEntity<>("La habitación a editar no se ha podido encontrar.", HttpStatus.BAD_REQUEST);

        appUserToUpdate.setName(app_user.getName());
        appUserToUpdate.setPassword(app_user.getPassword());

        if (appUserToUpdate.getName() != null && appUserToUpdate.getPassword() != null){
            return new ResponseEntity<>(db1AppUserRepository.save(appUserToUpdate),HttpStatus.CREATED);
        }

        return new ResponseEntity<>("Un valor no puede ser modificado por un valor nulo.", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "db2/{id}")
    public @ResponseBody
    ResponseEntity updateAppUser2(@PathVariable Integer id, @RequestBody Db2AppUserDTO app_user){
        Db2AppUser appUserToUpdate = db2AppUserRepository.findAppUserById(id);
        if (appUserToUpdate == null)
            return new ResponseEntity<>("La habitación a editar no se ha podido encontrar.", HttpStatus.BAD_REQUEST);

        appUserToUpdate.setName(app_user.getName());
        appUserToUpdate.setPassword(app_user.getPassword());

        if (appUserToUpdate.getName() != null && appUserToUpdate.getPassword() != null){
            return new ResponseEntity<>(db2AppUserRepository.save(appUserToUpdate),HttpStatus.CREATED);
        }

        return new ResponseEntity<>("Un valor no puede ser modificado por un valor nulo.", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "db1/{id}")
    public @ResponseBody
    ResponseEntity deleteAppUser1(@PathVariable Integer id){
        Db1AppUser appUserToDelete = db1AppUserRepository.findAppUserById(id);

        if (appUserToDelete != null){
            db1AppUserRepository.deleteById(id);
            return new ResponseEntity<>("Borrado exitosamente",HttpStatus.OK);
        }

        return new ResponseEntity<>("El usuario a borrar no existe.", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "db2/{id}")
    public @ResponseBody
    ResponseEntity deleteAppUser2(@PathVariable Integer id){
        Db2AppUser appUserToDelete = db2AppUserRepository.findAppUserById(id);

        if (appUserToDelete != null){
            db2AppUserRepository.deleteById(id);
            return new ResponseEntity<>("Borrado exitosamente",HttpStatus.OK);
        }

        return new ResponseEntity<>("El usuario a borrar no existe.", HttpStatus.BAD_REQUEST);
    }
}

