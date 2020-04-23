package ir.ac.kntu;


import org.jetbrains.annotations.NotNull;

import java.util.*;

public class TourLeaderList {
    private Set<TourLeader> tourLeaders;

    public TourLeaderList(){
        tourLeaders = new HashSet<>();
    }

    public Set<TourLeader> getTourLeaders() {
        return new HashSet<>(tourLeaders);
    }

    public void addTourLeader(TourLeader tourLeader){
        this.tourLeaders.add(tourLeader);
    }

    public void removeTourLeader(String name, String lastName){
        this.tourLeaders.removeAll(findTourLeaderByFullName(name, lastName).getTourLeaders());
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

    @NotNull
    private TourLeaderList findTourLeaderByFullName(String name, String lastName){
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
            if(i.getBirthDate().compare(date) == -1 || i.getBirthDate().compare(date) == 0){
                tourLeaders.addTourLeader(i);
            }
        }
        return tourLeaders;
    }

    public TourLeaderList findTourLeaderByUpperDateOfBirth(Date date){
        TourLeaderList tourLeaders = new TourLeaderList();
        for(TourLeader i: this.tourLeaders){
            if(i.getBirthDate().compare(date) == 1 || i.getBirthDate().compare(date) == 0){
                tourLeaders.addTourLeader(i);
            }
        }
        return tourLeaders;
    }

    public TourLeaderList findTourLeaderByBirthDateRange(@NotNull Date dateRange1, Date dateRange2){
        if(dateRange1.compare(dateRange2) == 1) {
            Date buffer = new Date(dateRange2);
            dateRange2 = dateRange1;
            dateRange1 = new Date(buffer);
        }
        return findTourLeaderByLowerDateOfBirth(dateRange2).findTourLeaderByUpperDateOfBirth(dateRange1);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        for(TourLeader i: tourLeaders){
            buffer.append(i.getName()).append(" ").append(i.getLastName()).append("\n");
        }
        if(buffer.length() > 1){
            return buffer.substring(0, buffer.length() - 1);
        }
        return buffer.toString();
    }
}