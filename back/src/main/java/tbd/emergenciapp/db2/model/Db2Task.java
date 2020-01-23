package tbd.emergenciapp.db2.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="task")
public class Db2Task implements Serializable {
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

    @ManyToOne(optional = false)
    @JoinColumn(name = "emergency_id", nullable = false)
    private Db2Emergency emergency;


    public Db2Task(Integer id,
                   String name,
                   Integer capacity,
                   String status)
    {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.status = status;
    }

    public Db2Task(){
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

    public Db2Emergency getEmergency() {
        return emergency;
    }

    public void setEmergency(Db2Emergency emergency) {
        this.emergency = emergency;
    }
}
