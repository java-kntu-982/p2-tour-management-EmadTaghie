package ir.ac.kntu;

import ir.ac.kntu.maputil.MapUtil;
import net.sf.saxon.expr.Component;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
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

    public void showSource(@NotNull Tour tour){
        showLocation(tour.getTourType().getSource());
    }

    public void showDist(@NotNull Tour tour){
        showLocation(tour.getTourType().getDestination());
    }

    public void showRout(@NotNull Tour tour){
        showLocation(tour.getTourType().getSource(), tour.getTourType().getDestination());
    }

    public void showCurrent(@NotNull Tour tour, @NotNull Date date){
        Date startDate = new Date(tour.getStartDate());
        Date endDate = new Date(tour.getStartDate().getYear(), tour.getStartDate().getMonth(),
                tour.getStartDate().getDay() + tour.getTourType().getTourDuration() - 1);
        if((date.compare(startDate) == 1 || date.compare(startDate) == 0) &&
                (date.compare(endDate) == -1 || date.compare(endDate) == 0)){
            for(Location toDo: tour.getTourType().getToDO()[date.getDay() - tour.getStartDate().getDay()]){
                showLocation(toDo);
            }
        }
    }

    public void showToDos(Tour tour){
        for(Set<Location> toDo: tour.getTourType().getToDO()){
            for(Location loc: toDo){
                for(String place: loc.getLocation().values()){
                    MapUtil.showMap(place);
                }
            }
        }
    }

    public static void main(String[] args) {
        Set<Location> locations = new HashSet<>();
        Set<Location> locations1 = new HashSet<>();
        locations1.add(new Location("sajjad", "sajjad"));
        locations1.add(new Location("Hassan", "Hassan"));
        Set<Location> locations2 = new HashSet<>();
        locations2.add(new Location("Majidie", "Majidie"));
        locations2.add(new Location("Hossein", "Hossein"));
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
        Tour tour = new Tour(tourLeader, 23, new Date(0, 1, 1), tourContainer);
        Map map = new Map();
        map.showRout(tour);
    }
}
