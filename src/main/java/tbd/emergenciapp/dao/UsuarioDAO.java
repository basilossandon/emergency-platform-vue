package tbd.emergenciapp.dao;
import tbd.emergenciapp.dto.UsuarioDTO;
import tbd.emergenciapp.model.Usuario;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface UsuarioDAO {
    List<Usuario> getAllUsuarios();
    Usuario getUsuarioById(Integer id);
    ResponseEntity createUsuario(UsuarioDTO usuario);
    ResponseEntity updateUsuario(Integer id,UsuarioDTO usuario);
    ResponseEntity deleteUsuario(Integer id);
}
