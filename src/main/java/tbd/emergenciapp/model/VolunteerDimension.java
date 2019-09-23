package tbd.emergenciapp.model;

import javax.persistence.*;

@Entity
public class VolunteerDimension {
    @EmbeddedId
    VolunteerDimensionKey id;

    @ManyToOne
    @MapsId("volunteer_id")
    @JoinColumn(name = "volunteer_id")
    Volunteer volunteer;

    @ManyToOne
    @MapsId("dimension_id")
    @JoinColumn(name = "dimension_id")
    Dimension dimension;

    float score;

    public VolunteerDimension(VolunteerDimensionKey id, Volunteer volunteer, Dimension dimension, float score) {
        this.id = id;
        this.volunteer = volunteer;
        this.dimension = dimension;
        this.score = score;
    }

    public VolunteerDimension() {
    }

    public VolunteerDimensionKey getId() {
        return id;
    }

    public void setId(VolunteerDimensionKey id) {
        this.id = id;
    }

    public Volunteer getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
