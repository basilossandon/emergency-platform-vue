package tbd.emergenciapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tbd.emergenciapp.dao.DimensionDAO;
import tbd.emergenciapp.dto.DimensionDTO;
import tbd.emergenciapp.model.Dimension;
import tbd.emergenciapp.repository.DimensionRepository;

import java.util.List;

@RestController
@Validated
@RequestMapping(path = "/dimensions")
@CrossOrigin(origins = "*")
public class DimensionController implements DimensionDAO {
    @Autowired
    private DimensionRepository dimensionRepository;

    @GetMapping("")
    @ResponseBody
    public List<Dimension> getAllDimensions(){
        return dimensionRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public Dimension getDimensionById(@PathVariable Integer id){
        return dimensionRepository.findDimensionById(id);
    }

    @PostMapping(value = "")
    public @ResponseBody
    ResponseEntity createDimension(@RequestBody DimensionDTO dimension){
        Dimension createdDimension = new Dimension();
        createdDimension.setName(dimension.getName());

        if(createdDimension.getName() != null){
            return new ResponseEntity<>(dimensionRepository.save(createdDimension), HttpStatus.CREATED);
        }

        return  new ResponseEntity<>("La dimension a crear no puede contener valores nulos.", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/{id}")
    public @ResponseBody
    ResponseEntity updateDimension(@PathVariable Integer id,@RequestBody DimensionDTO dimension){
        Dimension dimensionUpdate = dimensionRepository.findDimensionById(id);
        if(dimensionUpdate == null){
            return new ResponseEntity<>("La Dimension a editar no se ha podido encontrar. ", HttpStatus.BAD_REQUEST);
        }

        dimensionUpdate.setName(dimension.getName());

        if(dimensionUpdate.getName()!=null){
            return new ResponseEntity<>(dimensionRepository.save(dimensionUpdate),HttpStatus.CREATED);
        }

        return  new ResponseEntity<>("La dimension no puede ser modificado por un valor nulo.", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/{id}/delete")
    public  @ResponseBody
    ResponseEntity deleteDimension(@PathVariable Integer id){
        Dimension dimensionDelete = dimensionRepository.findDimensionById(id);

        if(dimensionDelete!=null){
            dimensionRepository.deleteById(id);
            return new ResponseEntity<>("Borrado exitosamente",HttpStatus.OK);
        }

        return new ResponseEntity("La dimension a borrar no existe. ", HttpStatus.BAD_REQUEST);
    }



}
