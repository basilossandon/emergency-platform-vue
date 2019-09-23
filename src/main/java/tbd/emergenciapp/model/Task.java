package tbd.emergenciapp.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="task")
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "capacity", nullable = false)
    private Integer capacity;
    @Column(name = "status", nullable = false)
    private String status;


    public Task(Integer id,
                String name,
                Integer capacity,
                String status)
    {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.status = status;
    }

    public Task(){
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

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
