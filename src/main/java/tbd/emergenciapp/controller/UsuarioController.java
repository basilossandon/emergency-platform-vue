package tbd.emergenciapp.controller;

import tbd.emergenciapp.dao.UsuarioDAO;
import tbd.emergenciapp.model.Usuario;
import tbd.emergenciapp.dto.UsuarioDTO;
import tbd.emergenciapp.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@Validated
@RequestMapping(path = "/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController implements UsuarioDAO {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("")
    @ResponseBody
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public Usuario getUsuarioById(@PathVariable Integer id) {
        return usuarioRepository.findUsuarioById(id);
    }

    @PostMapping(value = "")
    public @ResponseBody
    ResponseEntity createUsuario(@RequestBody UsuarioDTO usuario){
        Usuario createdUsuario = new Usuario();
        createdUsuario.setName(usuario.getName());
        createdUsuario.setPassword(usuario.getPassword());

        if (createdUsuario.getName() != null && createdUsuario.getPassword() != null){

            return new ResponseEntity<>(usuarioRepository.save(createdUsuario),HttpStatus.CREATED);
        }

        return new ResponseEntity<>("El usuario a crear no puede contener valores nulos.", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/{id}")
    public @ResponseBody
    ResponseEntity updateUsuario(@PathVariable Integer id, @RequestBody UsuarioDTO usuario){
        Usuario usuarioToUpdate = usuarioRepository.findUsuarioById(id);
        if (usuarioToUpdate == null)
            return new ResponseEntity<>("La habitación a editar no se ha podido encontrar.", HttpStatus.BAD_REQUEST);

        usuarioToUpdate.setName(usuario.getName());
        usuarioToUpdate.setPassword(usuario.getPassword());

        if (usuarioToUpdate.getName() != null && usuarioToUpdate.getPassword() != null){
            return new ResponseEntity<>(usuarioRepository.save(usuarioToUpdate),HttpStatus.CREATED);
        }

        return new ResponseEntity<>("Un valor no puede ser modificado por un valor nulo.", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteUsuario(@PathVariable Integer id) {

        Usuario usuarioToDelete = usuarioRepository.findUsuarioById(id);
        if(usuarioToDelete != null)
        {
            usuarioRepository.deleteById(id);
            return new ResponseEntity<>("Se ha borrado el usuario " + id + " exitosamente.", HttpStatus.OK);
        }
        else return new ResponseEntity<>("El usuario " + id + " no se encuentra.",HttpStatus.NOT_FOUND);
    }
}

