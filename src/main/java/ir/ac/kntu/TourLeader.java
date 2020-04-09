package ir.ac.kntu;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class TourLeader {
    private String name;
    private String lastName;
    private String nationalId;
    private String birthCertId;
    private Date birthDate;
    private Date hireDate;
    private Location acquaintedLocations;

    public TourLeader(){
        this("", "", "", "", new Date(0, 1, 1),
                new Date(0, 1, 1), new Location());
    }

    public TourLeader(TourLeader tourLeader){
        this(tourLeader.getName(),
                tourLeader.getLastName(),
                tourLeader.getNationalId(),
                tourLeader.getBirthCertId(),
                tourLeader.getBirthDate(),
                tourLeader.getHireDate(),
                tourLeader.getAcquaintedLocations());
    }

    public TourLeader(String name, String lastName, String nationalId, String birthCertId,
                      Date birthDate, Date hireDate, Location acquaintedLocations) {
        setName(name);
        setLastName(lastName);
        setNationalId(nationalId);
        setBirthCertId(birthCertId);
        setBirthDate(birthDate);
        setHireDate(hireDate);
        setAcquaintedLocations(acquaintedLocations);
    }

    public void setName(String name) {
        if(name != null) {
            this.name = name;
        }
        else{
            this.name = "";
        }
    }

    public void setLastName(String lastName){
        if(lastName != null) {
            this.lastName = lastName;
        }
        else{
            this.lastName = "";
        }
    }

    public void setNationalId(String nationalId) {
        if(nationalId.matches("[0-9]+")) {
            this.nationalId = nationalId;
        }
        else{
            this.nationalId = "";
        }
    }

    public void setBirthCertId(String birthCertId){
        if(birthCertId.matches("[0-9]+")) {
            this.birthCertId = birthCertId;
        }
        else{
            this.birthCertId = "";
        }
    }

    public void setBirthDate(Date birthDate) {
        if(birthDate != null) {
            this.birthDate = birthDate;
        }
        else{
            this.birthDate = new Date(0, 1, 1);
        }
    }

    public void setHireDate(Date hireDate) {
        if(hireDate != null) {
            this.hireDate = hireDate;
        }
        else{
            this.hireDate = new Date(0, 1, 1);
        }
    }

    public void setAcquaintedLocations(Location acquaintedLocations) {
        if(acquaintedLocations != null) {
            this.acquaintedLocations = acquaintedLocations;
        }
        else{
            this.acquaintedLocations = new Location();
        }
    }


    public String getName() {
        if(name == null){
            return "";
        }
        return new String(name);
    }

    public String getLastName() {
        if(lastName == null){
            return "";
        }
        return new String(lastName);
    }

    public String getNationalId() {
        if(nationalId == null){
            return "";
        }
        return new String(nationalId);
    }

    public String getBirthCertId() {
        if(birthCertId == null){
            return "";
        }
        return new String(birthCertId);
    }

    public Date getBirthDate() {
        if(birthDate == null){
            return new Date(0, 1, 1);
        }
        return new Date(birthDate);
    }

    public Date getHireDate() {
        if(hireDate == null){
            return new Date(0, 1, 1);
        }
        return new Date(hireDate);
    }

    public Location getAcquaintedLocations() {
        if(acquaintedLocations == null){
            return new Location();
        }
        return new Location(acquaintedLocations);
    }


    public void addAcquaintedLocations(Location location){
        this.acquaintedLocations.addLocation(location);
    }

    public void addAcquaintedLocations(Set<Location> location){
        for(Location i: location) {
            this.acquaintedLocations.addLocation(i);
        }
    }

    public void addAcquaintedLocations(String name, String coordinate){
        this.acquaintedLocations.addLocation(name, coordinate);
    }

    public void removeAcquaintedLocations(Location location){
        this.acquaintedLocations.removeLocation(location);
    }

    public void removeAcquaintedLocationsByName(String name){
        this.acquaintedLocations.removeLocationByName(name);
    }

    public void removeAcquaintedLocationsByCoordinate(String coordinate){
        this.acquaintedLocations.removeLocationByCoordinate(coordinate);
    }

    @Override
    public String toString() {
        return "name: " + name + '\n' +
                "lastName: " + lastName + '\n' +
                "nationalId: " + nationalId + '\n' +
                "birthCertId: " + birthCertId + '\n' +
                "birthDate: " + birthDate + "\n" +
                "hireDate: " + hireDate + "\n" +
                "acquaintedLocations:\n" + acquaintedLocations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TourLeader that = (TourLeader) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(nationalId, that.nationalId) &&
                Objects.equals(birthCertId, that.birthCertId) &&
                Objects.equals(birthDate, that.birthDate) &&
                Objects.equals(hireDate, that.hireDate) &&
                Objects.equals(acquaintedLocations, that.acquaintedLocations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, nationalId, birthCertId, birthDate, hireDate, acquaintedLocations);
    }

    public static void main(String[] args) {
        Location location = new Location();
        location.addLocation("Yazd", "Yazd");
        location.addLocation("Tehran", "Tehran");
        TourLeader tourLeader = new TourLeader("Emad", "Taghiye", "4420732474",
                "4420732474", new Date(1377, 2, 1), new Date(1395, 7 ,12),
                location);
        tourLeader.setLastName("amir");
        Location location1 = new Location();
        location1.addLocation("ahmad", "salam");
        location1.addLocation("ehsan", "khodafez");
        tourLeader.addAcquaintedLocations("sajjad", "erami");
        tourLeader.addAcquaintedLocations(location1);
        tourLeader.removeAcquaintedLocationsByCoordinate("salam");
        System.out.println(tourLeader);
    }
}
