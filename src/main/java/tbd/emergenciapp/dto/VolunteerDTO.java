package tbd.emergenciapp.dto;

public class VolunteerDTO {
    private String name;
    private Integer age;
    private String gender;


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

    public  String getGender(){
        return this.gender;
    }

    public  void  setGender(String gender){
        this.gender = gender;
    }
}
