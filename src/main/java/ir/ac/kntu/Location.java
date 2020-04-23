package ir.ac.kntu;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Location {
    private Map<String, String> location;

    public Location(){
        location = new HashMap<>();
    }

    public Location(@NotNull String location, @NotNull String coordinate){
        this.location = new HashMap<>();
        this.location.put(location, coordinate);
    }
    public Location(@NotNull Location location){
        this.location = location.getLocation();
    }

    public Map<String, String> getLocation() {
        return location;
    }

    public void addLocation(String name, String coordinate) {
        for(Map.Entry<String, String> i: location.entrySet()){
            if(i.getValue().equals(coordinate)){
                return;
            }
        }
        this.location.put(name, coordinate);
    }

    public void addLocation(@NotNull Location location){
            if(contains(location)){
                return;
            }
            for(Map.Entry<String, String> i: location.getLocation().entrySet()) {
                this.location.put(i.getKey(), i.getValue());
            }
    }

    public void removeLocation(@NotNull Location location){
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

    public boolean contains(@NotNull Location location){
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
        StringBuilder locData = new StringBuilder();
        for(Map.Entry<String, String> i: location.entrySet()){
            locData.append(i.getKey()).append(": ").append(i.getValue()).append("\n");
        }
        locData = new StringBuilder(locData.substring(0, locData.length() - 1));
        return locData.toString();
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
}
