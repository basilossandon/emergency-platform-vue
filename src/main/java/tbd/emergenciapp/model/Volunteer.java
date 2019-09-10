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
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "age", nullable = false)
    private Integer age;
    @Column(name = "sex", nullable = false)
    private String sex;

    public Volunteer(Integer id, String name, Integer age,String sex){
        this.id = id;
        this.name= name;
        this.age = age;
        this.sex = sex;
    }

    public Volunteer(){

    }
    public Integer getId(){
        return this.id;
    }

    public void  setId(Integer id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name=name;
    }

    public  Integer getAge(){
        return this.age;
    }

    public  void  setAge(Integer age){
        this.age = age;
    }

    public  String getSex(){
        return this.sex;
    }

    public void  setSex(String sex){
        this.sex = sex;
    }

}
