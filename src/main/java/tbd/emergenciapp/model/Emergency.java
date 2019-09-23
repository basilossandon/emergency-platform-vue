package tbd.emergenciapp.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="emergency")
public class Emergency implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "location", nullable = false)
    private String location;
    @Column(name = "status", nullable = false)
    private String status;

    @OneToMany(mappedBy = "emergency", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Task> tasks;

    public Emergency(Integer id,
                     String name,
                     String location,
                     String status)
    {
        this.id = id;
        this.name = name;
        this.location = location;
        this.status = status;
    }

    public Emergency(){}

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
}
