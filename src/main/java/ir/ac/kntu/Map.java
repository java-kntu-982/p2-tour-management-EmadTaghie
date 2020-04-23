package ir.ac.kntu;

import ir.ac.kntu.maputil.MapUtil;
import org.jetbrains.annotations.NotNull;
import java.util.Set;

public class Map {
    public void showLocation(Location location){
        for(String loc: location.getLocation().values()){
            MapUtil.showMap(loc);
            break;
        }
    }

    public void showLocation(Location location1, Location location2){
        String[] places = new String[2];
        for(String loc: location1.getLocation().values()){
            places[0] = loc;
            break;
        }

        for(String loc: location2.getLocation().values()){
            places[1] = loc;
            break;
        }
        MapUtil.showMap(places);
    }

    public void showCurrent(@NotNull Tour tour, @NotNull Date date){
        Date startDate = new Date(tour.getStartDate());
        Date endDate = new Date(tour.getStartDate().getYear(), tour.getStartDate().getMonth(),
                tour.getStartDate().getDay() + tour.getTourDuration() - 1);
        if((date.compare(startDate) == 1 || date.compare(startDate) == 0) &&
                (date.compare(endDate) == -1 || date.compare(endDate) == 0)){
            for(Location toDo: tour.getToDO()[date.getDay() - tour.getStartDate().getDay()]){
                showLocation(toDo);
            }
        }
    }

    public void showToDos(Tour tour){
        for(Set<Location> toDo: tour.getToDO()){
            for(Location loc: toDo){
                for(String place: loc.getLocation().values()){
                    MapUtil.showMap(place);
                }
            }
        }
    }
}
