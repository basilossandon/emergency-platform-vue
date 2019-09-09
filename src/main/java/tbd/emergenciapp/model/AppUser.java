package tbd.emergenciapp.model;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="app_user")
public class AppUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "password", nullable = false)
    private String password;

    public AppUser(Integer id,
                   String  name ,
                   String  password){
        this.id = id;
        this.password = password;
        this.name = name;
    }

    public AppUser(){
    }
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}