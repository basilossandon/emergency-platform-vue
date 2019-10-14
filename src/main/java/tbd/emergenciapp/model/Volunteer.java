package tbd.emergenciapp.model;


import javax.persistence.*;
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
    @Column(name = "sex")
    private String sex;
    @Column(name = "email")
    private String email;
    @Column(name = "lastname")
    private String lastname;
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


    public Volunteer(Integer id, String name, String sex, String email, String lastname, Float latitude, Float longitude, String rut, Integer strength, Integer dextery, Integer knowledge, Integer motivation, Integer leadership) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.email = email;
        this.lastname = lastname;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rut = rut;
        this.strength = strength;
        this.dextery = dextery;
        this.knowledge = knowledge;
        this.motivation = motivation;
        this.leadership = leadership;
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

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
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


    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getDextery() {
        return dextery;
    }

    public void setDextery(Integer dextery) {
        this.dextery = dextery;
    }

    public Integer getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(Integer knowledge) {
        this.knowledge = knowledge;
    }

    public Integer getMotivation() {
        return motivation;
    }

    public void setMotivation(Integer motivation) {
        this.motivation = motivation;
    }

    public Integer getLeadership() {
        return leadership;
    }

    public void setLeadership(Integer leadership) {
        this.leadership = leadership;
    }
}

