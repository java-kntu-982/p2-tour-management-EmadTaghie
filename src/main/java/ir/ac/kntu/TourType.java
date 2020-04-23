package ir.ac.kntu;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class TourType {
    final int minRange = 1;
    private int tourDuration;
    private int tourPrice;
    private int minParticipants;
    private int maxParticipants;
    private Location source;
    private Location destination;
    private WayOfTravel wayOfTravel;
    private Set<Location>[] toDO;

    public TourType(){
        setTourDuration(1);
        setTourPrice(1);
        setMinParticipants(1);
        setMaxParticipants(1);
        setSource(new Location());
        setDestination(new Location());
        setWayOfTravel(WayOfTravel.EarthTrip);
        setToDO(new Set[getTourDuration()]);
    }

    public TourType(@NotNull TourType tourType){
        setTourType(tourType);
    }

    public void setTourDuration(int tourDuration) {
        this.tourDuration = Math.max(minRange, tourDuration);
    }

    public void setTourPrice(int tourPrice) {
        this.tourPrice = Math.max(minRange, tourPrice);
    }

    public void setMinParticipants(int minParticipants) {
        this.minParticipants = Math.max(minRange, minParticipants);
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = Math.max(minRange, maxParticipants);
    }

    public void setSource(Location source) {
        this.source = source;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }

    public void setWayOfTravel(@NotNull WayOfTravel wayOfTravel) {
        this.wayOfTravel = wayOfTravel;
    }

    public void setToDO(@NotNull Set<Location>[] toDO) {
        if(tourDuration < 1){
            System.out.println("Enter tour Duration first");
            return;
        }
        if(toDO.length < tourDuration){
            this.toDO = new Set[tourDuration];
            for (int i = 0; i < toDO.length; i++){
                this.toDO[i].addAll(toDO[i]);
            }
            return;
        }
        this.toDO = toDO;
    }

    public void setTourType(@NotNull TourType tourType){
        setTourDuration(tourType.getTourDuration());
        setTourPrice(tourType.getTourPrice());
        setMinParticipants(tourType.getMinParticipants());
        setMaxParticipants(tourType.getMaxParticipants());
        setSource(tourType.getSource());
        setDestination(tourType.getDestination());
        setWayOfTravel(tourType.getWayOfTravel());
        setToDO(tourType.getToDO());
    }

    public int getTourDuration() {
        return tourDuration;
    }

    private int getTourPrice() {
        return tourPrice;
    }

    private int getMinParticipants() {
        return minParticipants;
    }

    private int getMaxParticipants() {
        return maxParticipants;
    }

    public Location getSource() {
        return new Location(source);
    }

    public Location getDestination() {
        return new Location(destination);
    }

    private WayOfTravel getWayOfTravel() {
        return wayOfTravel;
    }

    public Set<Location>[] getToDO() {
        return toDO.clone();
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        for (Set<Location> locations : toDO) {
            if (locations.size() == 0) {
                buffer.append("\n\tEmpty");
            }
            for (Location j : locations) {
                buffer.append("\n\t").append(j);
            }
        }
        buffer = new StringBuilder(buffer.substring(0, buffer.length() - 1));
        return "tourDuration: " + tourDuration + "\n" +
                "tourPrice: " + tourPrice + "\n" +
                "minParticipants: " + minParticipants + "\n" +
                "maxParticipants: " + maxParticipants + "\n" +
                "source: " + source + "\n" +
                "destination: " + destination + "\n" +
                "wayOfTravel: " + wayOfTravel + "\n" +
                "toDO: " + buffer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TourType that = (TourType) o;
        return tourDuration == that.tourDuration &&
                tourPrice == that.tourPrice &&
                minParticipants == that.minParticipants &&
                maxParticipants == that.maxParticipants &&
                Objects.equals(source, that.source) &&
                Objects.equals(destination, that.destination) &&
                wayOfTravel == that.wayOfTravel &&
                Arrays.equals(toDO, that.toDO);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(tourDuration, tourPrice, minParticipants, maxParticipants,
                source, destination, wayOfTravel);
        result = 31 * result + Arrays.hashCode(toDO);
        return result;
    }
}