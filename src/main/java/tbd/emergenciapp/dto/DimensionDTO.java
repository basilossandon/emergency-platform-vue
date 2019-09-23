package tbd.emergenciapp.dto;

import tbd.emergenciapp.model.VolunteerDimension;

import java.util.Set;

public class DimensionDTO {
    private String name;
    private Set<VolunteerDimension> volunteerDimensions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<VolunteerDimension> getVolunteerDimensions() {
        return volunteerDimensions;
    }

    public void setVolunteerDimensions(Set<VolunteerDimension> volunteerDimensions) {
        this.volunteerDimensions = volunteerDimensions;
    }
}
