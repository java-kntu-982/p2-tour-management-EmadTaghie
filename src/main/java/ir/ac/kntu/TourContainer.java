package ir.ac.kntu;

import java.util.*;

public class TourContainer {
    private int tourDuration;
    private int tourPrice;
    private int minParticipants;
    private int maxParticipants;
    private Location source;
    private Location destination;
    private WayOfTravel wayOfTravel;
    private Set<Location>[] toDO;

    public TourContainer(){
        this(1, 1, 1, 1, null,
                null, null, null);
    }

    public TourContainer(TourContainer tourContainer){
        this(tourContainer.getTourDuration(),
                tourContainer.getTourPrice(),
                tourContainer.getMinParticipants(),
                tourContainer.getMaxParticipants(),
                tourContainer.getSource(),
                tourContainer.getDestination(),
                tourContainer.getWayOfTravel(),
                tourContainer.getToDO());
    }

    public TourContainer(int tourDuration, int tourPrice, int minParticipants, int maxParticipants,
                         Location source, Location destination, WayOfTravel wayOfTravel, Set<Location>[] toDO) {
        setTourDuration(tourDuration);
        setTourPrice(tourPrice);
        setMinParticipants(minParticipants);
        setMaxParticipants(maxParticipants);
        setSource(source);
        setDestination(destination);
        setWayOfTravel(wayOfTravel);
        setToDO(toDO);
    }

    public void setTourDuration(int tourDuration) {
        if(tourDuration < 1){
            this.tourDuration = 1;
            return;
        }
        this.tourDuration = tourDuration;
    }

    public void setTourPrice(int tourPrice) {
        if(tourPrice < 1){
            this.tourPrice = 1;
            return;
        }
        this.tourPrice = tourPrice;
    }

    public void setMinParticipants(int minParticipants) {
        if(minParticipants < 1){
            this.minParticipants = 1;
            return;
        }
        this.minParticipants = minParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        if(maxParticipants < 1){
            this.maxParticipants = 1;
            return;
        }
        this.maxParticipants = maxParticipants;
    }

    public void setSource(Location source) {
        this.source = source;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }

    public void setWayOfTravel(WayOfTravel wayOfTravel) {
        if(wayOfTravel == null){
            this.wayOfTravel = WayOfTravel.FlightTrip;
            return;
        }
        this.wayOfTravel = wayOfTravel;
    }

    public void setToDO(Set<Location>[] toDO) {
        if(tourDuration < 1){
            System.out.println("Enter tour Duration first");
            return;
        }
        if(toDO == null){
            this.toDO = new Set[tourDuration];
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

    public int getTourDuration() {
        return tourDuration;
    }

    public int getTourPrice() {
        return tourPrice;
    }

    public int getMinParticipants() {
        return minParticipants;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public Location getSource() {
        return new Location(source);
    }

    public Location getDestination() {
        return new Location(destination);
    }

    public WayOfTravel getWayOfTravel() {
        return wayOfTravel;
    }

    public Set<Location>[] getToDO() {
        return toDO.clone();
    }

    @Override
    public String toString() {
        String buffer = "";
        int maxLength = 0;
        for(int i = 0; i < toDO.length; i++){
            if(toDO[i].size() == 0){
                buffer += "\n\tEmpty";
            }
            for(Location j: toDO[i]) {
                buffer +=  "\n\t" + j;
            }
        }
        buffer = buffer.substring(0, buffer.length() - 1);
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
        TourContainer that = (TourContainer) o;
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

    public static void main(String[] args) {
        Set<Location> locations = new HashSet<>();
        Set<Location> locations1 = new HashSet<>();
        locations1.add(new Location("sajjad", "sajjad"));
        Set<Location> locations2 = new HashSet<>();
        locations2.add(new Location("Majidie", "Majidie"));
        Set<Location>[] toDo = new Set[3];
        toDo[0] = locations;
        toDo[1] = locations1;
        toDo[2] = locations2;
        Location location = new Location();
        location.addLocation("Yazd", "Yazd");
        TourLeader tourLeader = new TourLeader("Emad", "Taghiye", "4420732474",
                "4420732474", new Date(1377, 2, 1), new Date(1395, 7 ,12),
                location);
        TourContainer tourContainer = new TourContainer(3, 10, 2, 3,
                new Location("Yazd", "Yazd"), new Location("Tehran", "Tehran"),
                WayOfTravel.EarthTrip, toDo);
        System.out.println(tourContainer);
    }
}