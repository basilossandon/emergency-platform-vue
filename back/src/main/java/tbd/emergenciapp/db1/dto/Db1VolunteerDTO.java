package tbd.emergenciapp.db1.dto;

public class Db1VolunteerDTO {
    private String name;
    private Integer age;
    private String sex;
    private String lastname;
    private Float latitude;
    private Float longitude;
    private String email;
    private String rut;
    private  Integer strength;
    private  Integer dextery;
    private Integer knowledge;
    private  Integer motivation;
    private  Integer leadership;

    public Db1VolunteerDTO() {
    }

    public  String getName(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge(){
        return this.age;
    }

    public void setAge(Integer age){
        this.age = age;
    }

    public  String getSex(){
        return this.sex;
    }

    public  void  setSex(String sex){
        this.sex = sex;
    }

    public String getLastName() {
        return this.lastname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public Float getLatitude() { return this.latitude; }

    public Float getLongitude() {
        return this.longitude;
    }

    public String getEmail() {
        return this.email;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setLastname(String lastname){
        this.lastname = lastname;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getDextery() {
        return dextery;
    }

    public void setDextery(Integer dextery) {
        this.dextery = dextery;
    }

    public Integer getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(Integer knowledge) {
        this.knowledge = knowledge;
    }

    public Integer getMotivation() {
        return motivation;
    }

    public void setMotivation(Integer motivation) {
        this.motivation = motivation;
    }

    public Integer getLeadership() {
        return leadership;
    }

    public void setLeadership(Integer leadership) {
        this.leadership = leadership;
    }
}
