package ir.ac.kntu;

import java.util.HashSet;
import java.util.Objects;

public class Tour {
    private TourLeader tourLeader;
    private int tourId;
    private Date startDate;
    private TourContainer tourType;

    public Tour(){
        this(new TourLeader(), 0, new Date(0, 1, 1), new TourContainer());
    }

    public Tour(TourLeader tourLeader , int tourId, Date startDate, TourContainer tourType){
        setTourLeader(tourLeader);
        setTourId(tourId);
        setStartDate(startDate);
        setTourType(tourType);
    }

    public void setTourLeader(TourLeader tourLeader){
        this.tourLeader = new TourLeader(tourLeader);
    }

    public void setTourId(int id){
        this.tourId = id;
    }

    public void setStartDate(Date startDate){
        if(startDate == null){
            this.startDate = new Date(0, 1, 1);
            return;
        }
        this.startDate = new Date(startDate);
    }

    public void setTourType(TourContainer tourType){
        this.tourType = new TourContainer(tourType);
    }

    public TourLeader getTourLeader(){
        return new TourLeader(tourLeader);
    }

    public int getTourId() {
        return tourId;
    }

    public Date getStartDate(){
        return startDate;
    }

    public TourContainer getTourType() {
        return tourType;
    }

    @Override
    public String toString() {
        return "tourLeader: " + tourLeader + "\n" +
                "tourId: " + tourId + "\n" +
                "startDate: " + startDate + "\n" +
                "tourType: " + tourType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tour tour = (Tour) o;
        return tourId == tour.tourId &&
                Objects.equals(tourLeader, tour.tourLeader) &&
                Objects.equals(startDate, tour.startDate) &&
                Objects.equals(tourType, tour.tourType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tourLeader, tourId, startDate, tourType);
    }
}
