package ir.ac.kntu;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class TourLeader extends Identity {
    private Location acquaintedLocations;

    public TourLeader(){
        this(new Identity(), new Location());
    }

    public TourLeader(@NotNull TourLeader tourLeader){
        this(tourLeader, tourLeader.getAcquaintedLocations());
    }

    private TourLeader(Identity identity, Location acquaintedLocations) {
        super(identity);
        setAcquaintedLocations(acquaintedLocations);
    }

    private void setAcquaintedLocations(Location acquaintedLocations) {
        this.acquaintedLocations = Objects.requireNonNullElseGet(acquaintedLocations, Location::new);
    }


    public Location getAcquaintedLocations() {
        return new Location(acquaintedLocations);
    }

    public void addAcquaintedLocations(String name, String coordinate){
        this.acquaintedLocations.addLocation(name, coordinate);
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + "acquaintedLocations:\n" + acquaintedLocations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TourLeader that = (TourLeader) o;
        return Objects.equals(acquaintedLocations, that.acquaintedLocations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), acquaintedLocations);
    }
}
