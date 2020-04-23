package ir.ac.kntu;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class TourList {
    private Set<Tour> tour;

    public TourList() {
        setTour(new Tour());
    }

    private void setTour(Tour tour) {
        this.tour = new HashSet<>();
        if (tour != null) {
            this.tour.add(tour);
        }
    }

    public Set<Tour> getTour() {
        return tour;
    }

    public void showTours() {
        StringBuilder buffer = new StringBuilder();
        for (Tour i : this.tour) {
            buffer.append(i.getDestination()).append(i.getTourId() != 0 ? Integer.toString(i.getTourId()) : "").append("\n");
        }
        buffer = buffer.length() > 1 ? new StringBuilder(buffer.substring(0, buffer.length() - 1)): buffer;
        System.out.println(buffer);
    }

    boolean isConflictDate(@NotNull Tour tour1, @NotNull Tour tour2) {
        Date start1 = new Date(tour1.getStartDate()), end1 = new Date(tour1.getStartDate());
        Date start2 = new Date(tour2.getStartDate()), end2 = new Date(tour2.getStartDate());
        end1.setDay(end1.getDay() + tour1.getTourDuration());
        end2.setDay(end2.getDay() + tour2.getTourDuration());
        return !(start1.compare(end2) == 1 || end1.compare(start2) == -1);
    }

    public Set<Tour> findTourByLeader(TourLeader tourLeader) {
        Set<Tour> tour = new HashSet<>();
        for (Tour i : this.tour) {
            if (i.getTourLeader().equals(tourLeader)) {
                tour.add(i);
            }
        }
        if (tour.size() == 0) {
            System.out.println("There is any thing for show.");
            return new HashSet<>();
        }
        return tour;
    }

    public Tour findTour(Location location, int ID) {
        Set<Tour> tours = new HashSet<>(findTourByLocation(location));
        for (Tour i : tours) {
            if (i.getTourId() == ID) {
                return i;
            }
        }
        return new Tour();
    }

    @NotNull
    private Set<Tour> findTourByLocation(Location location) {
        Set<Tour> tour = new HashSet<>();
        for (Tour i : this.tour) {
            if (i.getDestination().equals(location)) {
                tour.add(i);
            }
        }
        if (tour.size() == 0) {
            System.out.println("There is any thing for show.");
            return new HashSet<>();
        }
        return tour;
    }
}