package tbd.emergenciapp.dto;

import tbd.emergenciapp.model.Dimension;
import tbd.emergenciapp.model.VolunteerDimension;

import java.util.Set;

public class VolunteerDTO {
    private String name;
    private Integer age;
    private String sex;
    private Set<VolunteerDimension> volunteerDimensions;


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
}
