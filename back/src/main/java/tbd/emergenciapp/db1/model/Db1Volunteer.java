package tbd.emergenciapp.db1.model;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="volunteer")
public class Db1Volunteer implements Serializable {
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
    @Column(name = "latitude")
    private Float latitude;
    @Column(name = "longitude")
    private Float longitude;
    @Column(name = "rut")
    private String rut;
    @Column(name= "strength")
    private Integer strength;
    @Column(name = "dextery")
    private Integer dextery;
    @Column(name = "knowledge")
    private Integer knowledge;
    @Column(name = "motivation")
    private Integer motivation;
    @Column(name = "leadership")
    private Integer leadership;


//    @OneToMany(mappedBy = "volunteer")
//    private Set<VolunteerDimension> volunteerDimensions;


    public Db1Volunteer(Integer id, String name, String sex, String email, String lastname, Float latitude, Float longitude, String rut, int dextery, int strength, int knowledge, int motivation, int leadership) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.sex = sex;
        this.email = email;
        this.longitude = longitude;
        this.latitude = latitude;
        this.lastname = lastname;
        this.rut = rut;
        this.dextery = dextery;
        this.strength = strength;
        this.knowledge = knowledge;
        this.motivation = motivation;
        this.leadership = leadership;
    }



    public Db1Volunteer() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

