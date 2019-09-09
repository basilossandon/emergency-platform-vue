package tbd.emergenciapp.controller;

import tbd.emergenciapp.dao.AppUserDAO;
import tbd.emergenciapp.model.AppUser;
import tbd.emergenciapp.dto.AppUserDTO;
import tbd.emergenciapp.repository.AppUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@Validated
@RequestMapping(path = "/appusers")
@CrossOrigin(origins = "*")
public class AppUserController implements AppUserDAO {
    @Autowired
    private AppUserRepository appUserRepository;

    @GetMapping("")
    @ResponseBody
    public List<AppUser> getAllAppUsers() {
        return appUserRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public AppUser getAppUserById(@PathVariable Integer id) {
        return appUserRepository.findAppUserById(id);
    }

    @PostMapping(value = "")
    public @ResponseBody
    ResponseEntity createAppUser(@RequestBody AppUserDTO app_user){
        AppUser createdAppUser = new AppUser();
        createdAppUser.setName(app_user.getName());
        createdAppUser.setPassword(app_user.getPassword());

        if (createdAppUser.getName() != null && createdAppUser.getPassword() != null){

            return new ResponseEntity<>(appUserRepository.save(createdAppUser),HttpStatus.CREATED);
        }

        return new ResponseEntity<>("El usuario a crear no puede contener valores nulos.", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/{id}")
    public @ResponseBody
    ResponseEntity updateAppUser(@PathVariable Integer id, @RequestBody AppUserDTO app_user){
        AppUser appUserToUpdate = appUserRepository.findAppUserById(id);
        if (appUserToUpdate == null)
            return new ResponseEntity<>("La habitación a editar no se ha podido encontrar.", HttpStatus.BAD_REQUEST);

        appUserToUpdate.setName(app_user.getName());
        appUserToUpdate.setPassword(app_user.getPassword());

        if (appUserToUpdate.getName() != null && appUserToUpdate.getPassword() != null){
            return new ResponseEntity<>(appUserRepository.save(appUserToUpdate),HttpStatus.CREATED);
        }

        return new ResponseEntity<>("Un valor no puede ser modificado por un valor nulo.", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/{id}/delete")
    public @ResponseBody
    ResponseEntity deleteAppUser(@PathVariable Integer id){
        AppUser appUserToDelete = appUserRepository.findAppUserById(id);

        if (appUserToDelete != null){
            appUserRepository.deleteById(id);
            return new ResponseEntity<>("Borrado exitosamente",HttpStatus.OK);
        }

        return new ResponseEntity<>("El usuario a borrar no existe.", HttpStatus.BAD_REQUEST);
    }
}

