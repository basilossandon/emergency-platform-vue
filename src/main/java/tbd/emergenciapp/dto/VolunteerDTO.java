package tbd.emergenciapp.dto;

import tbd.emergenciapp.model.Dimension;
import tbd.emergenciapp.model.VolunteerDimension;

import java.util.Set;

public class VolunteerDTO {
    private String name;
    private Integer age;
    private String sex;
    private Set<VolunteerDimension> volunteerDimensions;
    private String lastname;
    private String latitude;
    private String longitude;
    private String email;
    private String rut;

    public VolunteerDTO() {
    }

    public  String getName(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge(){
        return this.age;
    }

    public void setAge(Integer age){
        this.age = age;
    }

    public  String getSex(){
        return this.sex;
    }

    public  void  setSex(String sex){
        this.sex = sex;
    }


    public Set<VolunteerDimension> getVolunteerDimensions() {
        return volunteerDimensions;
    }

    public void setVolunteerDimensions(Set<VolunteerDimension> volunteerDimensions) {
        this.volunteerDimensions = volunteerDimensions;
    }

    public String getLastName() {
        return this.lastname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public String getLatitude() {
        return this.latitude;

    }

    public String getLongitude() {
        return this.longitude;
    }

    public String getEmail() {
        return this.email;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setLastname(String lastname){
        this.lastname = lastname;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
