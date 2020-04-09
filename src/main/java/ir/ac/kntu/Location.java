package ir.ac.kntu;

import org.apache.commons.collections.map.HashedMap;

import java.util.Map;
import java.util.Objects;

public class Location {
    private Map<String, String> location;

    public Location(){
        location = new HashedMap();
    }

    public Location(String location, String coordinate){
        this.location = new HashedMap();
        if(location == null || coordinate == null){
            return;
        }
        this.location.put(location, coordinate);
    }
    public Location(Location location){
        if(location == null){
            this.location = new HashedMap();
            return;
        }
        this.location = location.getLocation();
    }

    public Map<String, String> getLocation() {
        if(location.size() == 0){
            return null;
        }
        return location;
    }

    public void setLocation(Map<String, String> location) {
        this.location = location;
    }

    public String getLocation(String name) {
        if(location.size() != 0){
        return location.get(name);
        }
        return null;
    }

    public void addLocation(String name, String coordinate) {
        if(location != null) {
            for(Map.Entry<String, String> i: location.entrySet()){
                if(i.getValue().equals(coordinate)){
                    return;
                }
            }
            this.location.put(name, coordinate);
        }
    }

    public void addLocation(Location location) {
        if(location != null) {
            for(Map.Entry<String, String> i: location.getLocation().entrySet()){
                for(Map.Entry<String, String> j: this.location.entrySet())
                if(i.getValue().equals(j.getValue())){
                    return;
                }
            }
            this.location.putAll(location.getLocation());
        }
    }

    public void removeLocationByName(String locationName){
        location.remove(locationName);
    }

    public void removeLocationByCoordinate(String locationCoordinate){
        for(Map.Entry<String, String> i: location.entrySet()) {
            if (i.getValue().equals(locationCoordinate)) {
                location.remove(i.getKey());
                break;
            }
        }
    }

    public void removeLocation(Location location){
        for(Map.Entry<String, String> i: location.getLocation().entrySet()) {
            for(Map.Entry<String, String> j: location.getLocation().entrySet()){
                if(i.getValue().equals(j.getValue()) && i.getKey().equals(j.getKey())){
                    this.location.remove(i.getKey());
                }
            }
        }
    }

    public void replaceCoordinate(String name, String newCoordinate){
        this.location.replace(name, newCoordinate);
    }

    public void replaceName(String coordinate, String newName){
        for(Map.Entry<String, String> i: location.entrySet()){
            if(i.getValue().equals(coordinate)){
                location.remove(i.getKey());
                addLocation(newName, coordinate);
                break;
            }
        }
    }
    public void clearMap(){
        this.location.clear();
    }

    public boolean contains(Location location){
        boolean containsLocation = true;
        for(Map.Entry<String, String> i: location.getLocation().entrySet()){
            containsLocation = containsLocation && this.location.containsKey(i.getKey())
                    && this.location.containsValue(i.getValue());
        }
        return containsLocation;
    }

    @Override
    public String toString() {
        if(location == null || location.size() == 0){
            return "There is anything for show";
        }
        String locData = "";
        for(Map.Entry<String, String> i: location.entrySet()){
            locData += i.getKey() + ": " + i.getValue() + "\n";
        }
        locData = locData.substring(0, locData.length() - 1);
        return locData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location1 = (Location) o;
        return Objects.equals(location, location1.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location);
    }

    public static void main(String[] args) {
        Location location = new Location();
        location.addLocation("Yazd", "Yazd");
        location.addLocation("Tehran", "Tehran");
        Location location1 = new Location();
        location1.addLocation("ahmad", "salam");
        location1.addLocation("ehsan", "khodafez");
        location.addLocation(location1);
        System.out.println(location);
    }
}
