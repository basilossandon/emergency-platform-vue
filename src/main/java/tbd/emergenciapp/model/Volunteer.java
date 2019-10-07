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
    @Column(name = "name")
    private String name;
    @Column(name = "sex")
    private String sex;
    @Column(name = "email")
    private String email;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "latitude")
    private String latitude;
    @Column(name = "longitude")
    private String longitude;
    @Column(name = "rut")
    private String rut;



    @OneToMany(mappedBy = "volunteer")
    private Set<VolunteerDimension> volunteerDimensions;


    public Volunteer(Integer id, String name, String sex, String email, String lastname, String latitude, String longitude,String rut) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.email = email;
        this.lastname = lastname;
        this.latitude = latitude;
        this.longitude = longitude;
        this.lastname = lastname;
        this.rut = rut;

    }

    public Volunteer() {
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
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

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }
}
