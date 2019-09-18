package tbd.emergenciapp.dto;

import tbd.emergenciapp.model.Dimension;

import java.util.Set;

public class VolunteerDTO {
    private String name;
    private Integer age;
    private String sex;
    private Set<Dimension> dimension;


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

    public Set<Dimension> getDimension() {
        return dimension;
    }

    public void setDimension(Set<Dimension> dimension) {
        this.dimension = dimension;
    }
}
