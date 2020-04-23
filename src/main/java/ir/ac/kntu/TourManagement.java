package ir.ac.kntu;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class TourManagement {
    private TourList tourList;
    private TourTypeList tourTypeList;
    private TourLeaderList tourLeaderList;
    private Location location;
    private Location tourDes = new Location();

    public TourManagement(){
        tourTypeList = new TourTypeList();
        tourList = new TourList();
        tourLeaderList = new TourLeaderList();
        location = new Location();
    }

    public Location getLocation(){
        return location;
    }

    public TourTypeList getTourTypeList(){
        return tourTypeList;
    }

    public TourLeaderList getTourLeaderList(){
        return this.tourLeaderList;
    }

    public TourList getTourList(){
        return tourList;
    }

    private boolean isInLocation(TourType tourType){
        return this.location != null &&tourType != null && this.location.contains(
                tourType.getSource()) && this.location.contains(tourType.getDestination());
    }

    private boolean isInLocation(TourLeader tourLeader){
        return this.location != null && tourLeader != null &&
                this.location.contains(tourLeader.getAcquaintedLocations());
    }

    public void addTourType(TourType tourType){
        if(isInLocation(tourType)) {
            this.tourTypeList.getTourType().add(tourType);
            return;
        }
        System.out.println("There are one or more locations that are not in location list. Add them please.");
    }

    public void addLeader(@NotNull TourLeader tourLeader){
        if(isInLocation(tourLeader)){
            tourLeaderList.addTourLeader(tourLeader);
            return;
        }
        System.out.println("One or more tour leaders acquainted location are broader as tour types that we have. add their knowledge");
    }

    public void addTour(Tour tour){
        if(!tourTypeList.getTourType().contains(tour)) {
            System.out.println("One or more tour are not in tour type list. add them please.");
            return;
        }

        if(!tourLeaderList.getTourLeaders().contains(tour.getTourLeader())){
            System.out.println("Can not find one or more tour leaders in tour leader list. add them please.");
            return;
        }

        if(!tour.getTourLeader().getAcquaintedLocations().contains(tour.getDestination())){
            System.out.println("One or more tour leaders are nor acquainted with entered locations");
            return;
        }

        for(Tour i: tourList.findTourByLeader(tour.getTourLeader())) {
            if(tourList.isConflictDate(tour, i)) {
                System.out.println("One or more tour leaders aren't in touch.");
                return;
            }
        }

        this.tourList.getTour().add(tour);
    }

    @Override
    public String toString() {
        return "tourType: " + tourTypeList.getTourType() + "\n" +
                "tour: " + tourList.getTour() + "\n" +
                "tourLeaderList: " + tourLeaderList + "\n" +
                "location: " + location + "\n" +
                "tourDes: " + tourDes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TourManagement tourManagement = (TourManagement) o;
        return Objects.equals(tourTypeList.getTourType(), tourManagement.tourTypeList.getTourType()) &&
                Objects.equals(tourList.getTour(), tourManagement.tourList.getTour()) &&
                Objects.equals(tourLeaderList, tourManagement.tourLeaderList) &&
                Objects.equals(location, tourManagement.location) &&
                Objects.equals(tourDes, tourManagement.tourDes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tourTypeList.getTourType(), tourList.getTour(), tourLeaderList, location, tourDes);
    }
}