package ir.ac.kntu;


import org.jetbrains.annotations.NotNull;

import java.util.*;

public class TourLeaderList {
    private Set<TourLeader> tourLeaders;

    public TourLeaderList(){
        tourLeaders = new HashSet<>();
    }

    public TourLeaderList(Set<TourLeader> tourLeaders){
        if(tourLeaders != null || tourLeaders.size() != 0){
            setTourLeaders(tourLeaders);
        }
    }

    public TourLeaderList(@NotNull TourLeaderList tourLeaders){
        if(tourLeaders.getTourLeaders() != null || tourLeaders.size() != 0){
            setTourLeaders(tourLeaders);
        }
    }

    public void setTourLeaders(Set<TourLeader> tourLeaders) {
        if(tourLeaders != null || tourLeaders.size() != 0) {
            this.tourLeaders = tourLeaders;
        }
    }

    public void setTourLeaders(TourLeaderList tourLeaders) {
        if(tourLeaders != null || tourLeaders.size() != 0) {
            this.tourLeaders = tourLeaders.getTourLeaders();
        }
    }

    public Set<TourLeader> getTourLeaders() {
        return new HashSet<>(tourLeaders);
    }

    public int size(){
        return tourLeaders.size();
    }

    public void addTourLeader(TourLeader tourLeader){
        this.tourLeaders.add(tourLeader);
    }

    public void addTourLeaders(TourLeaderList tourLeaderList){
        this.tourLeaders.addAll(tourLeaderList.getTourLeaders());
    }

    public void addTourLeaders(Set<TourLeader> tourLeaders){
        this.tourLeaders.addAll(tourLeaders);
    }

    public void removeTourLeader(TourLeader tourLeader){
        this.tourLeaders.remove(tourLeader);
    }

    public void removeTourLeader(String name, String lastName){
        TourLeaderList tourLeaderList = new TourLeaderList();
        tourLeaderList = findTourLeaderByFullName(name, lastName);
        this.tourLeaders.removeAll(tourLeaderList.getTourLeaders());
    }

    public void removeTourLeader(TourLeaderList tourLeaderList){
        this.tourLeaders.removeAll(tourLeaderList.getTourLeaders());
    }

    public void removeTourLeader(Set<TourLeader> tourLeaders){
        this.tourLeaders.remove(tourLeaders);
    }


    public TourLeaderList findTourLeaderByName(String name){
        TourLeaderList tourLeaders = new TourLeaderList();
        for(TourLeader i: this.tourLeaders){
            if(i.getName().equals(name)){
                tourLeaders.addTourLeader(i);
            }
        }
        return tourLeaders;
    }

    public TourLeaderList findTourLeaderByLastName(String lastName){
        TourLeaderList tourLeaders = new TourLeaderList();
        for(TourLeader i: this.tourLeaders){
            if(i.getLastName().equals(lastName)){
                tourLeaders.addTourLeader(i);
            }
        }
        return tourLeaders;
    }

    public TourLeaderList findTourLeaderByFullName(String name, String lastName){
        TourLeaderList tourLeaderList = new TourLeaderList();
        for(TourLeader i: this.tourLeaders){
            if(i.getName().equals(name) && i.getLastName().equals(lastName)){
                tourLeaderList.addTourLeader(i);
            }
        }
        return tourLeaderList;
    }

    public TourLeader findSpecTourLeaderByFullName(String name, String lastName){
        TourLeader tourLeader = new TourLeader();
        for(TourLeader i: this.tourLeaders){
            if(i.getName().equals(name) && i.getLastName().equals(lastName)){
                tourLeader = i;
                break;
            }
        }
        return tourLeader;
    }

    public TourLeaderList findTourLeaderByLocation(Location location){
        TourLeaderList tourLeaders = new TourLeaderList();
        for(TourLeader i: this.tourLeaders){
            if(i.getAcquaintedLocations().contains(location)){
                tourLeaders.addTourLeader(i);
            }
        }
        return tourLeaders;
    }

    public TourLeaderList findTourLeaderByDateOfBirth(Date date){
        TourLeaderList tourLeaders = new TourLeaderList();
        for(TourLeader i: this.tourLeaders){
            if(i.getBirthDate().compare(date) == 0){
                tourLeaders.addTourLeader(i);
            }
        }
        return tourLeaders;
    }

    public TourLeaderList findTourLeaderByLowerDateOfBirth(Date date){
        TourLeaderList tourLeaders = new TourLeaderList();
        for(TourLeader i: this.tourLeaders){
            if(i.getBirthDate().compare(date) == -1){
                tourLeaders.addTourLeader(i);
            }
        }
        return tourLeaders;
    }

    public TourLeaderList findTourLeaderByUpperDateOfBirth(Date date){
        TourLeaderList tourLeaders = new TourLeaderList();
        for(TourLeader i: this.tourLeaders){
            if(i.getBirthDate().compare(date) == 1){
                tourLeaders.addTourLeader(i);
            }
        }
        return tourLeaders;
    }

    public TourLeaderList findTourLeaderByBirthDateRange(Date dateRange1, Date dateRange2){
        TourLeaderList tourLeaders = new TourLeaderList();
        if(dateRange1.compare(dateRange2) == 1) {
            Date buffer = new Date(dateRange2);
            dateRange2 = dateRange1;
            dateRange1 = new Date(buffer);
        }
        for(TourLeader i: this.tourLeaders){
            if((i.getBirthDate().compare(dateRange1) == 1 || i.getBirthDate().compare(dateRange1) == 0) &&
                    (i.getBirthDate().compare(dateRange2) == -1 || i.getBirthDate().compare(dateRange2) == 0)){
                tourLeaders.addTourLeader(i);
            }
        }
        return tourLeaders;
    }

    @Override
    public String toString() {
        String buffer = "";
        for(TourLeader i: tourLeaders){
            buffer += i.getName() + " " + i.getLastName() + "\n";
        }
        if(buffer.length() > 1){
            return buffer.substring(0, buffer.length() - 1);
        }
        return buffer;
    }

    public static void main(String[] args) {
        Location location = new Location();
        Location location1 = new Location();
        Location location2 = new Location();
        location.addLocation("Yazd", "Yazd");
        location1.addLocation("Tehran", "Yazd");
        location2.addLocation("Yazd", "Yazd");
        Date date1 = new Date(1370, 1, 1);
        Date date2 = new Date(1377, 2, 2);
        TourLeaderList tourLeaderList = new TourLeaderList();
        TourLeader tourLeader = new TourLeader();
        tourLeaderList.addTourLeader(new TourLeader("Emad", "Taghiye", "4420732474",
                "4420732474", new Date(1377, 2, 1), new Date(1395, 7 ,12), location));
        tourLeaderList.addTourLeader(new TourLeader("Sajjad", "Taghiye", "4420732474",
                "4420732474", new Date(1370, 1, 31), new Date(1395, 7 ,12), location));
        System.out.println(tourLeaderList.findTourLeaderByBirthDateRange(date2, date1));
    }
}
