package ir.ac.kntu;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class TourList {
    private Set<TourContainer> tourType;
    private Set<Tour> tour;
    private TourLeaderList tourLeaderList;
    private Location location;
    private Location tourDes = new Location();

    public TourList(){
        this(new HashSet<>(), new HashSet<>(), new TourLeaderList(), new Location());
    }

    public TourList(Set<TourContainer> tourType, Set<Tour> tour, TourLeaderList tourLeaderList, Location location){
//        if(!isInLocation(tourType)){
//            System.out.println("There is one or more locations in tour type that are not in locations");
//            setTourType(new TourContainer());
//            setTour(new Tour());
//            setTourLeaderList(new TourLeaderList());
//            setLocation(new Location());
//            return;
//        }
//        if(!isInTourType(tour, tourType)){
//            System.out.println("There is one or more tours that are not in tour type. add them first2");
//            setTourType(new TourContainer());
//            setTour(new Tour());
//            setTourLeaderList(new TourLeaderList());
//            setLocation(new Location());
//            return;
//        }
//        if(!isInTourLeaderList(tour, tourLeaderList)) {
//            System.out.println("There is any tour leaders with this details. Add tour leaders first1");
//            setTourType(new TourContainer());
//            setTour(new Tour());
//            setTourLeaderList(new TourLeaderList());
//            setLocation(new Location());
//            return;
//        }
        if(tour != null && tourLeaderList != null && tourType != null && location != null){
            setTourType(tourType);
            setTour(tour);
            setTourLeaderList(tourLeaderList);
            setLocation(location);
            return;
        }
        setTourType(new TourContainer());
        setTour(new Tour());
        setTourLeaderList(new TourLeaderList());
        setLocation(new Location());
    }

    public TourList(Set<TourContainer> tourType, Tour tour, TourLeaderList tourLeaderList, Location location) {
        if(!isInLocation(tourType)){
            System.out.println("There is one or more locations in tour type that are not in locations");
            setTourType(new TourContainer());
            setTour(new Tour());
            setTourLeaderList(new TourLeaderList());
            setLocation(new Location());
            return;
        }
        if(!isInTourType(tour, tourType)){
            System.out.println("There is one or more tours that are not in tour type. add them first2");
            setTourType(new TourContainer());
            setTour(new Tour());
            setTourLeaderList(new TourLeaderList());
            setLocation(new Location());
            return;
        }
        if(!isInTourLeaderList(tour, tourLeaderList)) {
            System.out.println("There is any tour leaders with this details. Add tour leaders first1");
            setTourType(new TourContainer());
            setTour(new Tour());
            setTourLeaderList(new TourLeaderList());
            setLocation(new Location());
            return;
        }
        if (tour != null && tourType != null && tourLeaderList != null && location != null){
            setTourType(tourType);
            setTour(tour);
            setTourLeaderList(tourLeaderList);
            setLocation(location);
            return;
        }
        setTourType(new TourContainer());
        setTour(new Tour());
        setTourLeaderList(new TourLeaderList());
        setLocation(new Location());
    }

    public TourList(TourContainer tourType, Tour tour, TourLeaderList tourLeaderList, Location location){
        if(!isInLocation(tourType)){
            System.out.println("There is one or more locations in tour type that are not in locations");
            setTourType(new TourContainer());
            setTour(new Tour());
            setTourLeaderList(new TourLeaderList());
            setLocation(new Location());
            return;
        }
        if(!isInTourType(tour, tourType)){
            System.out.println("There is one or more tours that are not in tour type. add them first2");
            setTourType(new TourContainer());
            setTour(new Tour());
            setTourLeaderList(new TourLeaderList());
            setLocation(new Location());
            return;
        }
        if(!isInTourLeaderList(tour, tourLeaderList)) {
            System.out.println("There is any tour leaders with this details. Add tour leaders first1");
            setTourType(new TourContainer());
            setTour(new Tour());
            setTourLeaderList(new TourLeaderList());
            setLocation(new Location());
            return;
        }
        if(tour != null && tourType != null && tourLeaderList != null) {
            setTourType(tourType);
            setTour(tour);
            setTourLeaderList(tourLeaderList);
            setLocation(location);
            return;
        }
        setTourType(new TourContainer());
        setTour(new Tour());
        setTourLeaderList(new TourLeaderList());
        setLocation(location);
    }

    public Location getLocation(){
        return location;
    }

    public void setTourType(Set<TourContainer> tourType) {
        if(tourType != null) {
            this.tourType = new HashSet<>(tourType);
            return;
        }
        this.tourType = new HashSet<>();
    }

    public void setTourType(TourContainer tourType) {
        if(tourType != null) {
            this.tourType = new HashSet<>();
            this.tourType.add(tourType);
            return;
        }
        this.tourType = new HashSet<>();
    }

    public void setTour(Set<Tour> tour){
        if(tour != null){
            this.tour = new HashSet<>(tour);
            return;
        }
        this.tour = new HashSet<>();
    }

    public void setTour(Tour tour){
        if(tour != null){
            this.tour = new HashSet<>();
            this.tour.add(tour);
            return;
        }
        this.tour = new HashSet<>();
    }

    public void setLocation(Location location){
        if(location != null){
            this.location = location;
            return;
        }
        this.location = new Location();
    }

    public void setTourLeaderList(TourLeaderList tourLeaderList){
        if(tourLeaderList != null){
            this.tourLeaderList = new TourLeaderList(tourLeaderList);
            return;
        }
        this.tourLeaderList = new TourLeaderList();
    }

    private boolean isInTourType(@NotNull Set<Tour> tour, Set<TourContainer> tourType){
        if(tour == null || tour == null){
            return false;
        }
        for(Tour i: tour){
            if(!tourType.contains(i.getTourType())){
                return false;
            }
        }
        return true;
    }

    private boolean isInTourType(@NotNull Tour tour, @NotNull Set<TourContainer> tourType){
        if(tour == null || tourType == null){
            return false;
        }
        if(!tourType.contains(tour.getTourType())){
            return false;
        }
        return true;
    }

    private boolean isInTourType(@NotNull Tour tour, @NotNull TourContainer tourType){
        if(tour == null || tourType == null){
            return false;
        }
        return tourType.equals(tour.getTourType());
    }

    private boolean isInTourLeaderList(@NotNull Set<Tour> tour, TourLeaderList tourLeaderList){
        if(tour == null || tourLeaderList == null){
            return false;
        }
        for(Tour i: tour){
            if(!tourLeaderList.getTourLeaders().contains(i.getTourLeader())){
                return false;
            }
        }
        return true;
    }

    private boolean isInTourLeaderList(@NotNull Tour tour, @NotNull TourLeaderList tourLeaderList){
        if(tour == null || tourLeaderList == null){
            return false;
        }
        return tourLeaderList.getTourLeaders().contains(tour.getTourLeader());
    }

    private boolean isInLocation(TourContainer tourType){
        if(this.location != null &&tourType != null){
            return this.location.contains(tourType.getSource()) && this.location.contains(tourType.getDestination());
        }
        return false;
    }

    private boolean isInLocation(TourLeader tourLeader){
        if(this.location != null && tourLeader != null){
            return this.location.contains(tourLeader.getAcquaintedLocations());
        }
        return false;
    }

    private boolean isInLocation(Set<TourContainer> tourType){
        int counterTrue = 0, counterLoop = 0;
        if(tourType == null || location == null){
            return false;
        }
        for(TourContainer i: tourType){
            if(location.contains(i.getDestination()) && location.contains(i.getSource())){
                counterTrue++;
            }
            counterLoop++;
        }
        return counterLoop == counterTrue;
    }

    public Set<TourContainer> getTourTypes(){
        return this.tourType;
    }

    public Set<Tour> getTour(){
        return this.tour;
    }

    public TourLeaderList getTourLeaderList(){
        return this.tourLeaderList;
    }

    public void showTourTypes(){
        String buffer = "";
        for(TourContainer i: this.tourType){
            buffer += i.getDestination() + "\n";
        }
        if(buffer.length() > 1){
            buffer = buffer.substring(0, buffer.length() - 1);
        }
        System.out.println(buffer);
    }

    public void showTours(){
        String buffer = "";
        for(Tour i: this.tour){
            buffer += i.getTourType().getDestination() + (i.getTourId() != 0 ? Integer.toString(i.getTourId()):"") + "\n";
        }
        if(buffer.length() > 1){
            buffer = buffer.substring(0, buffer.length() - 1);
        }
        System.out.println(buffer);
    }

    public void addTourType(TourContainer tourType){
        if(isInLocation(tourType)) {
            this.tourType.add(tourType);
            return;
        }
        System.out.println("There are one or more locations that are not in location list. Add them please.");
    }

    public void addTourTypes(Set<TourContainer> tourType){
        this.tourType.addAll(tourType);
    }

    public void addLeader(@NotNull TourLeader tourLeader){
        if(isInLocation(tourLeader)){
            tourLeaderList.addTourLeader(tourLeader);
            return;
        }
        System.out.println("One or more tour leaders acquainted location are broader as tour types that we have. add their knowledge");
    }

    public void addLeaders(@NotNull TourLeaderList tourLeaderList){
        for(TourLeader i: tourLeaderList.getTourLeaders()){
            addLeader(i);
        }
    }

    public void addTour(Tour tour){
        if(!tourType.contains(tour.getTourType())) {
            System.out.println("One or more tour are not in tour type list. add them please.");
            return;
        }

        if(!tourLeaderList.getTourLeaders().contains(tour.getTourLeader())){
            System.out.println("Can not find one or more tour leaders in tour leader list. add them please.");
            return;
        }

        if(!tour.getTourLeader().getAcquaintedLocations().contains(tour.getTourType().getDestination())){
            System.out.println("One or more tour leaders are nor acquainted with entered locations");
            return;
        }

        for(Tour i: findTourByLeader(tour.getTourLeader())) {
            if(isConflictDate(tour, i)) {
                System.out.println("One or more tour leaders aren't in touch.");
                return;
            }
        }

        this.tour.add(tour);
    }

    public void addTours(@NotNull Set<Tour> tour){
        for(Tour i: tour){
            addTour(i);
        }
    }

    public void removeTourType(TourContainer tourType){
        this.tourType.remove(tourType);
    }

    public void removeTourTypes(Set<TourContainer> tourType){
        this.tourType.removeAll(tourType);
    }

    public void removeTour(Tour tour){
        this.tour.remove(tour);
    }

    public void removeTours(Set<Tour> tour){
        this.tour.removeAll(tour);
    }

    private boolean isInTour(TourLeader tourLeader){
        if(tourLeader != null && tour != null){
            for(Tour i: tour){
                if(i.getTourLeader().equals(tourLeader)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isConflictDate(@NotNull Tour tour1, @NotNull Tour tour2){
        Date start1 = new Date(tour1.getStartDate()), end1 = new Date(tour1.getStartDate());
        Date start2 = new Date(tour2.getStartDate()), end2 = new Date(tour2.getStartDate());
        end1.setDay(end1.getDay() + tour1.getTourType().getTourDuration());
        end2.setDay(end2.getDay() + tour2.getTourType().getTourDuration());
        if(start1.compare(end2) == 1 || end1.compare(start2) == -1){
            return false;
        }
        return true;
    }

    public Set<Tour> findTourByLeader(TourLeader tourLeader){
        Set<Tour> tour = new HashSet<>();
        for(Tour i: this.tour){
            if(i.getTourLeader().equals(tourLeader)){
                tour.add(i);
            }
        }
        if(tour.size() == 0){
            System.out.println("There is any thing for show.");
            return new HashSet<>();
        }
        return tour;
    }

    public Set<Tour> findTourByDate(Date startDate){
        Set<Tour> tour = new HashSet<>();
        for(Tour i: this.tour){
            if(i.getStartDate().equals(startDate)){
                tour.add(i);
            }
        }
        if(tour.size() == 0){
            System.out.println("There is any thing for show.");
            return new HashSet<>();
        }
        return tour;
    }

    public Tour findTour(Location location, int ID){
        Set<Tour> tours = new HashSet<>();
        tours = findTourByLocation(location);
        for(Tour i: tours){
            if(i.getTourId() == ID){
                return i;
            }
        }
        return new Tour();
    }

    public Set<Tour> findTourByEndDate(Date endDate){
        Set<Tour> tour = new HashSet<>();
        Date end = new Date(0, 1, 1);
        for(Tour i: this.tour){
            end = i.getStartDate();
            end.setDay(end.getDay() + i.getTourType().getTourDuration());
            if(end.equals(endDate)){
                tour.add(i);
            }
        }
        if(tour.size() == 0){
            System.out.println("There is any thing for show.");
            return new HashSet<>();
        }
        return tour;
    }

    public Set<Tour> findTourAfterDate(Date date){
        Set<Tour> tour = new HashSet<>();
        for(Tour i: this.tour){
            if(date.compare(i.getStartDate()) == 1){
                tour.add(i);
            }
        }
        if(tour.size() == 0){
            System.out.println("There is any thing for show.");
            return new HashSet<>();
        }
        return tour;
    }

    public Set<Tour> findTourBeforeDate(Date date){
        Set<Tour> tour = new HashSet<>();
        for(Tour i: this.tour){
            if(date.compare(i.getStartDate()) == -1){
                tour.add(i);
            }
        }
        if(tour.size() == 0){
            System.out.println("There is any thing for show.");
            return new HashSet<>();
        }
        return tour;
    }

    public Set<Tour> findTourBetweenDate(Date date1, Date date2){
        Date greaterDate = new Date(date1), lesserDate = new Date(date2);
        if(date1.compare(date2) == 1){
            Date buffer = new Date(date1);
            date1 = new Date(date2);
            date2 = buffer;
        }
        Set<Tour> tour = new HashSet<>();
        for(Tour i: this.tour){
            if(date1.compare(i.getStartDate()) == -1 && date2.compare(i.getStartDate()) == 1){
                tour.add(i);
            }
        }
        if(tour.size() == 0){
            System.out.println("There is any thing for show.");
            return new HashSet<>();
        }
        return tour;
    }

    public Set<Tour> findTourByDuration(int duration){
        Set<Tour> tour = new HashSet<>();
        for(Tour i: this.tour){
            if(i.getTourType().getTourDuration() == duration){
                tour.add(i);
            }
        }
        if(tour.size() == 0){
            System.out.println("There is any thing for show.");
            return new HashSet<>();
        }
        return tour;
    }

    public Set<Tour> findTourByMinParticipants(int minPart){
        Set<Tour> tour = new HashSet<>();
        for(Tour i: this.tour){
            if(i.getTourType().getMinParticipants() >= minPart){
                tour.add(i);
            }
        }
        if(tour.size() == 0){
            System.out.println("There is any thing for show.");
            return new HashSet<>();
        }
        return tour;
    }

    public Set<Tour> findTourByMaxParticipants(int maxPart){
        Set<Tour> tour = new HashSet<>();
        for(Tour i: this.tour){
            if(i.getTourType().getMinParticipants() <= maxPart){
                tour.add(i);
            }
        }
        if(tour.size() == 0){
            System.out.println("There is any thing for show.");
            return new HashSet<>();
        }
        return tour;
    }

    public Set<Tour> findTourByToDo(Location location){
        Set<Tour> tour = new HashSet<>();
        Set<Location> todoLoc = new HashSet<>();
        for(Tour i: this.tour){
            todoLoc.addAll(i.getTourType().getToDO()[0]);
            todoLoc.addAll(i.getTourType().getToDO()[1]);
            todoLoc.addAll(i.getTourType().getToDO()[2]);
            if(todoLoc.contains(location)){
                tour.add(i);
            }
            todoLoc.clear();
        }if(tour.size() == 0){
            System.out.println("There is any thing for show.");
            return new HashSet<>();
        }
        return tour;
    }

    public Set<Tour> findTourByLocation(Location location){
        Set<Tour> tour = new HashSet<>();
        for(Tour i: this.tour){
            if(i.getTourType().getDestination().equals(location)){
                tour.add(i);
            }
        }
        if(tour.size() == 0){
            System.out.println("There is any thing for show.");
            return new HashSet<>();
        }
        return tour;
    }

    public Set<Tour> findTourByPrice(int price){
        Set<Tour> tour = new HashSet<>();
        for(Tour i: this.tour){
            if(i.getTourType().getTourPrice() == price){
                tour.add(i);
            }
        }
        if(tour.size() == 0){
            System.out.println("There is any thing for show.");
            return new HashSet<>();
        }
        return tour;
    }

    public Set<Tour> findTourByGreaterPrice(int price){
        Set<Tour> tour = new HashSet<>();
        for(Tour i: this.tour){
            if(i.getTourType().getTourPrice() >= price){
                tour.add(i);
            }
        }
        if(tour.size() == 0){
            System.out.println("There is any thing for show.");
            return new HashSet<>();
        }
        return tour;
    }

    public Set<Tour> findTourByLesserPrice(int price){
        Set<Tour> tour = new HashSet<>();
        for(Tour i: this.tour){
            if(i.getTourType().getTourPrice() <= price){
                tour.add(i);
            }
        }
        if(tour.size() == 0){
            System.out.println("There is any thing for show.");
            return new HashSet<>();
        }
        return tour;
    }

    public Set<Tour> findTourByBetweenPrice(int price1, int price2){
        if(price1 > price2){
            int buffer = price1;
            price1 = price2;
            price2 = buffer;
        }
        Set<Tour> tour = new HashSet<>();
        for(Tour i: this.tour){
            if(i.getTourType().getTourPrice() >= price1 && i.getTourType().getTourPrice() <= price2){
                tour.add(i);
            }
        }
        if(tour.size() == 0){
            System.out.println("There is any thing for show.");
            return new HashSet<>();
        }
        return tour;
    }

    public Set<TourContainer> findTourTypeByDuration(int duration){
        Set<TourContainer> tour = new HashSet<>();
        for(TourContainer i: this.tourType){
            if(i.getTourDuration() == duration){
                tour.add(i);
            }
        }
        if(tour.size() == 0){
            System.out.println("There is any thing for show.");
            return new HashSet<>();
        }
        return tour;
    }

    public Set<TourContainer> findTourTypeByMinParticipants(int minPart){
        Set<TourContainer> tour = new HashSet<>();
        for(TourContainer i: this.tourType){
            if(i.getMinParticipants() >= minPart){
                tour.add(i);
            }
        }
        if(tour.size() == 0){
            System.out.println("There is any thing for show.");
            return new HashSet<>();
        }
        return tour;
    }

    public Set<TourContainer> findTourTypeByMaxParticipants(int maxPart){
        Set<TourContainer> tour = new HashSet<>();
        for(TourContainer i: this.tourType){
            if(i.getMinParticipants() <= maxPart){
                tour.add(i);
            }
        }
        if(tour.size() == 0){
            System.out.println("There is any thing for show.");
            return new HashSet<>();
        }
        return tour;
    }

    public Set<TourContainer> findTourTypeByToDo(Location location){
        Set<TourContainer> tour = new HashSet<>();
        Set<Location> todoLoc = new HashSet<>();
        for(TourContainer i: this.tourType){
            todoLoc.addAll(i.getToDO()[0]);
            todoLoc.addAll(i.getToDO()[1]);
            todoLoc.addAll(i.getToDO()[2]);
            if(todoLoc.contains(location)){
                tour.add(i);
            }
            todoLoc.clear();
        }if(tour.size() == 0){
            System.out.println("There is any thing for show.");
            return new HashSet<>();
        }
        return tour;
    }

    public Set<TourContainer> findTourTypeByLocation(Location location){
        Set<TourContainer> tour = new HashSet<>();
        for(TourContainer i: this.tourType){
            if(i.getDestination().equals(location)){
                tour.add(i);
            }
        }
        if(tour.size() == 0){
            System.out.println("There is any thing for show.");
            return new HashSet<>();
        }
        return tour;
    }

    public Set<TourContainer> findTourTypeByPrice(int price){
        Set<TourContainer> tour = new HashSet<>();
        for(TourContainer i: this.tourType){
            if(i.getTourPrice() == price){
                tour.add(i);
            }
        }
        if(tour.size() == 0){
            System.out.println("There is any thing for show.");
            return new HashSet<>();
        }
        return tour;
    }

    public Set<TourContainer> findTourTypeByGreaterPrice(int price){
        Set<TourContainer> tour = new HashSet<>();
        for(TourContainer i: this.tourType){
            if(i.getTourPrice() >= price){
                tour.add(i);
            }
        }
        if(tour.size() == 0){
            System.out.println("There is any thing for show.");
            return new HashSet<>();
        }
        return tour;
    }

    public Set<TourContainer> findTourTypeByLesserPrice(int price){
        Set<TourContainer> tour = new HashSet<>();
        for(TourContainer i: this.tourType){
            if(i.getTourPrice() <= price){
                tour.add(i);
            }
        }
        if(tour.size() == 0){
            System.out.println("There is any thing for show.");
            return new HashSet<>();
        }
        return tour;
    }

    public Set<TourContainer> findTourTypeByBetweenPrice(int price1, int price2){
        if(price1 > price2){
            int buffer = price1;
            price1 = price2;
            price2 = buffer;
        }
        Set<TourContainer> tour = new HashSet<>();
        for(TourContainer i: this.tourType){
            if(i.getTourPrice() >= price1 && i.getTourPrice() <= price2){
                tour.add(i);
            }
        }
        if(tour.size() == 0){
            System.out.println("There is any thing for show.");
            return new HashSet<>();
        }
        return tour;
    }

    private Location getTourDes(){
        tourDes.clearMap();
        for(TourContainer i: tourType){
            tourDes.addLocation(i.getDestination());
        }
        return tourDes;
    }

    @Override
    public String toString() {
        return "tourType: " + tourType + "\n" +
                "tour: " + tour + "\n" +
                "tourLeaderList: " + tourLeaderList + "\n" +
                "location: " + location + "\n" +
                "tourDes: " + tourDes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TourList tourList = (TourList) o;
        return Objects.equals(tourType, tourList.tourType) &&
                Objects.equals(tour, tourList.tour) &&
                Objects.equals(tourLeaderList, tourList.tourLeaderList) &&
                Objects.equals(location, tourList.location) &&
                Objects.equals(tourDes, tourList.tourDes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tourType, tour, tourLeaderList, location, tourDes);
    }

    public static void main(String[] args) {
        TourLeaderList tourLeaderList = new TourLeaderList();
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
        Location location3 = new Location();
        location3.addLocation("Yazd", "Yazd");
        TourLeader tourLeader = new TourLeader("Emad", "Taghiye", "4420732474",
                "4420732474", new Date(1377, 2, 1), new Date(1395, 7 ,12),
                location3);
        tourLeader.addAcquaintedLocations(locations1);
        tourLeader.addAcquaintedLocations(locations2);
        tourLeader.addAcquaintedLocations(new Location("Tehran", "Tehran"));
        tourLeaderList.addTourLeader(tourLeader);
        TourContainer tourContainer = new TourContainer(3, 10, 2, 3,
                new Location("Yazd", "Yazd"), new Location("Tehran", "Tehran"),
                WayOfTravel.EarthTrip, toDo);
        TourContainer tourContainer1 = new TourContainer(3, 10, 2, 3,
                new Location("Yazd", "Yazd"), new Location("Karaj", "Karaj"),
                WayOfTravel.EarthTrip, toDo);
        Tour tour = new Tour(tourLeader, 23, new Date(0, 1, 1), tourContainer);
//        TourList tourList = new TourList(tourContainer, tour, tourLeaderList, location);
//        tourList.addTour(new Tour(tourLeader, 23, new Date(0, 1, 5), tourContainer));
//        tourList.addTourType(tourContainer1);
//        tourList.showTours();
    }
}