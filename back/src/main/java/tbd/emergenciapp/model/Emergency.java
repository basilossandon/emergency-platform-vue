package tbd.emergenciapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "location", nullable = false)
    private String location;
    @Column(name = "status", nullable = false)
    private String status;
    @Column(name = "longitude", nullable = false)
    private Float longitude;
    @Column(name = "latitude", nullable = false)
    private Float latitude;
    @JsonBackReference
    @OneToMany(mappedBy = "emergency", cascade = CascadeType.ALL)
    private Set<Task> tasks;

    public Emergency(Integer id,
                     String name,
                     String location,
                     String status,
                     Float latitude,
                     Float longitude)
    {
        this.id = id;
        this.name = name;
        this.location = location;
        this.status = status;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Emergency(){}

    public Float getLongitude() {        return longitude;    }

    public void setLongitude(Float longitude) {        this.longitude = longitude;    }

    public Float getLatitude() {        return latitude;    }

    public void setLatitude(Float latitude) {      this.latitude = latitude;    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

}
