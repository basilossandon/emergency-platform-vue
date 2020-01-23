package tbd.emergenciapp.db2.model;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="app_user")
public class Db2AppUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "role")
    private String role;
    public Db2AppUser(Integer id,
                      String  name ,
                      String  password,
                      String  role,
                      String email){
        this.id = id;
        this.password = password;
        this.name = name;
        this.role = role;
        this.email= email;
    }

    public Db2AppUser(){
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

    public void setName(String name) { this.name = name;}

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }
}