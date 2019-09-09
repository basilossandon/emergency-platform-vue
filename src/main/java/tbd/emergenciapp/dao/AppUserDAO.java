package tbd.emergenciapp.dao;
import tbd.emergenciapp.dto.AppUserDTO;
import tbd.emergenciapp.model.AppUser;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface AppUserDAO {
    List<AppUser> getAllAppUsers();
    AppUser getAppUserById(Integer id);
    ResponseEntity createAppUser(AppUserDTO app_user);
    ResponseEntity updateAppUser(Integer id,AppUserDTO app_user);
    ResponseEntity deleteAppUser(Integer id);
}
