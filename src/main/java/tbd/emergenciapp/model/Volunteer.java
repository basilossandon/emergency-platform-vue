package tbd.emergenciapp.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="volunteer")
public class Volunteer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "age", nullable = false)
    private Integer age;
    @Column(name = "sex", nullable = false)
    private String sex;

    @OneToMany(mappedBy = "volunteer")
    Set<VolunteerDimension> volunteerDimensions;

    public Volunteer(Integer id, String name, Integer age, String sex, Set<VolunteerDimension> volunteerDimensions) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.volunteerDimensions = volunteerDimensions;
    }

    public Volunteer() {
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Set<VolunteerDimension> getVolunteerDimensions() {
        return volunteerDimensions;
    }

    public void setVolunteerDimensions(Set<VolunteerDimension> volunteerDimensions) {
        this.volunteerDimensions = volunteerDimensions;
    }
}
