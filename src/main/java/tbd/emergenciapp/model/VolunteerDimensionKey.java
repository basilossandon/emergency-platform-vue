package tbd.emergenciapp.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class VolunteerDimensionKey implements Serializable {

    @Column(name = "volunteer_id")
    Integer volunteer_id;

    @Column(name = "dimension_id")
    Integer dimension_id;


    public VolunteerDimensionKey(Integer volunteer_id,Integer dimension_id) {
        this.volunteer_id = volunteer_id;
        this.dimension_id = dimension_id;
    }

    public  VolunteerDimensionKey(){

    }

    public Integer getVolunteer_id() {
        return volunteer_id;
    }

    public void setVolunteer_id(Integer volunteer_id) {
        this.volunteer_id = volunteer_id;
    }

    public Integer getDimension_id() {
        return dimension_id;
    }

    public void setDimension_id(Integer dimension_id) {
        this.dimension_id = dimension_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VolunteerDimensionKey that = (VolunteerDimensionKey) o;
        return volunteer_id.equals(that.volunteer_id) &&
                dimension_id.equals(that.dimension_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(volunteer_id, dimension_id);
    }
}
