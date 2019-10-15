package tbd.emergenciapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tbd.emergenciapp.model.Volunteer;
import tbd.emergenciapp.repository.VolunteerRepository;
import tbd.emergenciapp.utilities.EntityManagerUtils;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(path = "/multivolunteer")
public class MultiVolunteerController {

    @Autowired private VolunteerRepository repository;
    @Autowired private HttpServletRequest context;
    @Autowired private EntityManagerUtils emUtils;

    @GetMapping("")
    @ResponseBody
    public List<Volunteer> getAllVolunteers(){
        setRepository( context.getRequestURL().toString());
        return repository.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public Volunteer getVolunteerById(@PathVariable Integer id){
        setRepository( context.getRequestURL().toString());
        return repository.findVolunteerById(id);
    }

    private void setRepository(String url) {
        repository = emUtils.getJpaFactory(url).getRepository(VolunteerRepository.class);
    }

}
