package tbd.emergenciapp.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dimension")
public class Dimension implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;

    public Dimension(Integer id,String name){
        this.id = id;
        this.name = name;
    }

    public Dimension(){

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
}
