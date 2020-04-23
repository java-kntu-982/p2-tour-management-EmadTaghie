package ir.ac.kntu;

import java.util.Objects;

public class Tour extends TourType{
    private TourLeader tourLeader;
    private int tourId;
    private Date startDate;

    public Tour(){
        setTourLeader(new TourLeader());
        setTourId(0);
        setStartDate(new Date(0, 1, 1));
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

    public TourLeader getTourLeader(){
        return new TourLeader(tourLeader);
    }

    public int getTourId() {
        return tourId;
    }

    public Date getStartDate(){
        return startDate;
    }

    @Override
    public String toString() {
        return "tourLeader: " + tourLeader + "\n" +
                "tourId: " + tourId + "\n" +
                "startDate: " + startDate + "\n" +
                "tourType: " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Tour tour = (Tour) o;
        return tourId == tour.tourId &&
                tourLeader.equals(tour.tourLeader) &&
                startDate.equals(tour.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tourLeader, tourId, startDate);
    }
}
