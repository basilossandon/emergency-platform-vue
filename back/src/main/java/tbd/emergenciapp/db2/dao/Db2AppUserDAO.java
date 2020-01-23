package tbd.emergenciapp.db2.dao;

import org.springframework.http.ResponseEntity;
import tbd.emergenciapp.db2.dto.Db2AppUserDTO;
import tbd.emergenciapp.db2.model.Db2AppUser;


import java.util.List;


public interface Db2AppUserDAO {
    List<Db2AppUser> getAllAppUsers2();
    Db2AppUser getAppUserById2(Integer id);
    ResponseEntity createAppUser2(Db2AppUserDTO app_user);
    ResponseEntity updateAppUser2(Integer id, Db2AppUserDTO app_user);
    ResponseEntity deleteAppUser2(Integer id);
}
