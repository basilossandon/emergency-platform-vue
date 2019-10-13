package tbd.emergenciapp.dto;

public class EmergencyDTO {
    private String name;
    private String location;
    private String status;
    private Float latitude;
    private Float longitude;

    public Float getLatitude() { return latitude;}

    public Float getLongitude() { return longitude;}
    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }
    public void setLongitude(Float longitude) {
        this.longitude = longitude;
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
}
