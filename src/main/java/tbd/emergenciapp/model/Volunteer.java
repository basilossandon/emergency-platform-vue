package tbd.emergenciapp.model;


import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.awt.*;
import java.io.Serializable;


@Entity
@Table(name="volunteer")
public class Volunteer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "email")
    private String email;
    @Column(name = "sex")
    private String sex;
    @Column(name = "longitude")
    private String longitude;
    @Column(name = "latitude")
    private Float latitude;
    @Column(name = "longitude")
    private Float longitude;
    @Column(name = "rut")
    private String rut;
    @Column(name= "strength")
    private  Integer strength;
    @Column(name = "dextery")
    private  Integer dextery;
    @Column(name = "knowledge")
    private Integer knowledge;
    @Column(name = "motivation")
    private  Integer motivation;
    @Column(name = "leadership")
    private  Integer leadership;

    @Column(name = "location", columnDefinition = "geometry(Point,4326)")
    private Point location;


    @OneToMany(mappedBy = "volunteer")
    private Set<VolunteerDimension> volunteerDimensions;


    public Volunteer(Integer id, String name, String sex, String email, String lastname, String latitude, String longitude,String rut) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.sex = sex;
        this.email = email;
        this.longitude = longitude;
        this.latitude = latitude;
        this.longitude = longitude;
        this.lastname = lastname;
        this.rut = rut;

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

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
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

