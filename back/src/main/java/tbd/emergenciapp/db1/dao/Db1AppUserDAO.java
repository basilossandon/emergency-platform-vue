package tbd.emergenciapp.db1.dao;

import org.springframework.http.ResponseEntity;
import tbd.emergenciapp.db1.dto.Db1AppUserDTO;
import tbd.emergenciapp.db1.model.Db1AppUser;


import java.util.List;

public interface Db1AppUserDAO {
    List<Db1AppUser> getAllAppUsers1();
    Db1AppUser getAppUserById1(Integer id);
    ResponseEntity createAppUser1(Db1AppUserDTO app_user);
    ResponseEntity updateAppUser1(Integer id, Db1AppUserDTO app_user);
    ResponseEntity deleteAppUser1(Integer id);
}
