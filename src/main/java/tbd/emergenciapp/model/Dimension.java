package tbd.emergenciapp.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "dimension")
public class Dimension implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "dimension")
    Set<VolunteerDimension> volunteerDimensions;


    public Dimension(Integer id, String name, Set<VolunteerDimension> volunteerDimensions) {
        this.id = id;
        this.name = name;
        this.volunteerDimensions = volunteerDimensions;
    }


    public Dimension(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<VolunteerDimension> getVolunteerDimensions() {
        return volunteerDimensions;
    }

    public void setVolunteerDimensions(Set<VolunteerDimension> volunteerDimensions) {
        this.volunteerDimensions = volunteerDimensions;
    }
}
