package ir.ac.kntu;

import java.util.HashSet;
import java.util.Set;

public class TourTypeList {
    private Set<TourType> tourType;

    public TourTypeList() {
        setTourType(new TourType());
    }

    private void setTourType(TourType tourType) {
        this.tourType = new HashSet<>();
        if (tourType != null) {
            this.tourType.add(tourType);
        }
    }

    public Set<TourType> getTourType() {
        return tourType;
    }

    public void showTourTypes() {
        StringBuilder buffer = new StringBuilder();
        for (TourType i : this.tourType) {
            buffer.append(i.getDestination()).append("\n");
        }
        buffer = buffer.length() > 1 ? new StringBuilder(buffer.substring(0, buffer.length() - 1)): buffer;
        System.out.println(buffer);
    }

    public Set<TourType> findTourTypeByLocation(Location location) {
        Set<TourType> tour = new HashSet<>();
        for (TourType i : this.tourType) {
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