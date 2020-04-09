package ir.ac.kntu;

import org.checkerframework.checker.regex.qual.Regex;
import org.jetbrains.annotations.NotNull;

import java.io.Console;
import java.io.Flushable;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.ConsoleHandler;

public class Menu {
    Scanner input;
    String pointer;
    TourList tourList;

    public Menu(){
        input = new Scanner(System.in);
        pointer = "1";
        tourList = new TourList();
    }

    public void setDefault(){
        pointer = "1";
    }

    public void showDefault() {
        System.out.println("Enter the number of option you want to see");
        System.out.println("1- Leaders");
        System.out.println("2- Tours");
        System.out.println("3- Locations");
        System.out.println("4- Maps");

        String buffer = input.next();
        if(!buffer.matches("[1-4]")){
            clearScreen();
            System.out.println("Incorrect input.");
            return;
        }
        pointer += buffer;
        clearScreen();
    }

    public void showLeaders(){
        String buffer;
        System.out.println("1- Show leaders");
        System.out.println("2- Add leader");
        System.out.println("3- Remove leader");
        System.out.println("4- Edit leader");
        System.out.println("5- FindLeader");
        System.out.println("6- Back");
        checkInput(6);
    }

    public void showLeaderList(){
        if(tourList == null){
            System.out.println("There is any tour.");
            waitInput();
            return;
        }

        if(tourList.getTourLeaderList().getTourLeaders() == null ||
                tourList.getTourLeaderList().getTourLeaders().size() == 0){
            back();
            System.out.println("There is any tourLeader in the list.");
            return;
        }
        System.out.println(tourList.getTourLeaderList().getTourLeaders());
        System.out.println("Press 1 for back");
        String buffer = input.next();
        if(buffer.equals("1")){
            back();
        }
    }

    public void addLeader(){
        clearScreen();
        TourLeader tourLeader = new TourLeader();
        String data;
        tourLeader.setName(checkUntil("[a-zA-Z][a-zA-Z ]+", "Enter name",
                "Incorrect name. Try again."));
        tourLeader.setLastName(checkUntil("[a-zA-Z][a-zA-Z ]+", "Enter last name",
                "Incorrect last name. Try again."));
        tourLeader.setNationalId(checkUntil("[1-9][0-9]+", "Enter national Id",
                "Incorrect national Id. Try again."));
        tourLeader.setBirthCertId(checkUntil("[1-9][0-9]+", "Enter birth certificate Id",
                "Incorrect birth certificate Id. Try again."));
        data = checkUntil("[0-9]+-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])",
                "Enter birth date as year-month-day",
                "Incorrect birth date");
        tourLeader.setBirthDate(new Date(Integer.parseInt(data.split("-")[0]),
                Integer.parseInt(data.split("-")[1]), Integer.parseInt(data.split("-")[2])));
        data = checkUntil("[0-9]+-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])",
                "Enter hire date as year-month-day",
                "Incorrect hire date");
        tourLeader.setHireDate(new Date(Integer.parseInt(data.split("-")[0]),
                Integer.parseInt(data.split("-")[1]), Integer.parseInt(data.split("-")[2])));
        System.out.println("Enter acquainted locations");
        System.out.println("press 1 if you want to exit");
        while(!data.equals("1")){
            data = input.nextLine();
            if(data.matches("[a-zA-Z][a-zA-Z ]+:([a-zA-Z][a-zA-Z ]+|@[0-9]+)")){
                tourLeader.addAcquaintedLocations(data.split(":")[0], data.split(":")[1]);
            }
            else if(!data.equals("1")){
                System.out.println("Incorrect location");
            }
        }
        tourList.addLeader(tourLeader);
        System.out.println("Press Enter for continue");
        input.nextLine();
        back();
    }

    public void removeLeader(){
        tourList.getTourLeaderList().removeTourLeader(checkUntil("[a-zA-Z][a-zA-Z ]+","Enter leader name",
                "Incorrect name"), checkUntil("[a-zA-Z][a-zA-Z ]+","Enter leader last name",
                "Incorrect last name"));
        back();
    }

    public void editLeader(){
        String name = checkUntil("[a-zA-Z][a-zA-Z ]+", "Enter leader name", "Incorrect name");
        String lastName = checkUntil("[a-zA-Z][a-zA-Z ]+", "Enter leader last name", "Incorrect last name");
        TourLeader tourLeader = tourList.getTourLeaderList().findSpecTourLeaderByFullName(name, lastName);
        if(tourLeader.equals(new TourLeader())){
            back();
            System.out.println("Can not find the leader");
            return;
        }
        String buffer;
        System.out.println("1- Change name");
        System.out.println("2- Change last name");
        System.out.println("3- Change national Id");
        System.out.println("4- Change birth certificate Id");
        System.out.println("5- Change birth date");
        System.out.println("6- Change hire date");
        System.out.println("7- Change acquainted locations");
        System.out.println("8- Back");
        checkInput(8);
        if(pointer.length() > "114".length()){
            switch(pointer.substring(pointer.length() - 1)){
                case "1":
                    buffer = checkUntil("[a-zA-Z][a-zA-Z ]+", "Enter leader name", "Incorrect input");
                    tourLeader.setName(buffer);
                    break;
                case "2":
                    buffer = checkUntil("[a-zA-Z][a-zA-Z ]+", "Enter leader last name", "Incorrect input");
                    tourLeader.setLastName(buffer);
                    break;
                case "3":
                    buffer = checkUntil("[1-9][0-9]+", "Enter leader national Id",
                            "Incorrect input");
                    tourLeader.setNationalId(buffer);
                    break;
                case "4":
                    buffer = checkUntil("[1-9][0-9]+", "Enter leader birth certificate Id",
                            "Incorrect input");
                    tourLeader.setBirthCertId(buffer);
                    break;
                case "5":
                    buffer = checkUntil("[0-9]+-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])",
                            "Enter birth date as year-month-day", "Incorrect birth date");
                    tourLeader.setBirthDate(new Date(Integer.parseInt(buffer.split("-")[0]),
                            Integer.parseInt(buffer.split("-")[1]),
                            Integer.parseInt(buffer.split("-")[2])));
                    break;
                case "6":
                    buffer = checkUntil("[0-9]+-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])",
                            "Enter hire date as year-month-day", "Incorrect hire date");
                    tourLeader.setHireDate(new Date(Integer.parseInt(buffer.split("-")[0]),
                            Integer.parseInt(buffer.split("-")[1]),
                            Integer.parseInt(buffer.split("-")[2])));
                    break;
                case "7":
                    buffer = checkUntil("[a-zA-Z][a-zA-Z ]+:([a-zA-Z][a-zA-Z ]+|@[0-9]+)",
                            "Enter Location as locationName:coordinate",
                            "Incorrect input");
                    tourLeader.getAcquaintedLocations().getLocation().remove(buffer.split(":")[0],
                            buffer.split(":")[1]);
                    buffer = checkUntil("[a-zA-Z][a-zA-Z ]+:([a-zA-Z][a-zA-Z ]+|@[0-9]+)",
                            "Enter new Location as locationName:coordinate",
                            "Incorrect input");
                    tourLeader.getAcquaintedLocations().addLocation(buffer.split(":")[0],
                            buffer.split(":")[1]);
                    break;
            }
            System.out.println("Press Enter for continue");
            input.nextLine();
            back();
        }

    }

    public void findLeader(){
        String data;
        System.out.println("1- Find leader by name");
        System.out.println("2- Find leader by last name");
        System.out.println("3- Find leader by acquainted location");
        System.out.println("4- Find leader by birth date");
        System.out.println("5- Find leader by older than specific date");
        System.out.println("6- find leader by younger than specific date");
        System.out.println("7-find leader by between date");
        System.out.println("8- back");
        String buffer = checkInput(8);
        if(!buffer.equals("")){
            switch(buffer){
                case "1":
                    data = checkUntil("[a-zA-Z][a-zA-Z ]+", "Enter leader name", "incorrect input");
                    System.out.println(tourList.getTourLeaderList().findTourLeaderByName(data));
                    break;
                case "2":
                    data = checkUntil("[a-zA-Z][a-zA-Z ]+", "Enter leader last name", "incorrect input");
                    System.out.println(tourList.getTourLeaderList().findTourLeaderByLastName(data));
                    break;
                case "3":
                    data = checkUntil("[a-zA-Z][a-zA-Z ]+:([a-zA-Z][a-zA-Z ]+|@[0-9]+)",
                            "Enter Location as locationName:coordinate",
                            "Incorrect input");
                    System.out.println(tourList.getTourLeaderList().findTourLeaderByLocation(
                            new Location(data.split(":")[0], data.split(":")[1])));
                    break;
                case "4":
                    data = checkUntil("[0-9]+-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])",
                            "Enter birth date as year-month-day", "Incorrect birth date");
                    System.out.println(tourList.getTourLeaderList().findTourLeaderByDateOfBirth(
                            new Date(Integer.parseInt(data.split("-")[0]),
                                    Integer.parseInt(data.split("-")[1]),
                                    Integer.parseInt(data.split("-")[2]))));
                    break;
                case "5":
                    data = checkUntil("[0-9]+-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])",
                            "Enter birth date as year-month-day", "Incorrect birth date");
                    System.out.println(tourList.getTourLeaderList().findTourLeaderByLowerDateOfBirth(
                            new Date(Integer.parseInt(data.split("-")[0]),
                                    Integer.parseInt(data.split("-")[1]),
                                    Integer.parseInt(data.split("-")[2]))));
                    break;
                case "6":
                    data = checkUntil("[0-9]+-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])",
                            "Enter birth date as year-month-day", "Incorrect birth date");
                    System.out.println(tourList.getTourLeaderList().findTourLeaderByUpperDateOfBirth(
                            new Date(Integer.parseInt(data.split("-")[0]),
                                    Integer.parseInt(data.split("-")[1]),
                                    Integer.parseInt(data.split("-")[2]))));
                    break;
                case "7":
                    data = checkUntil("[0-9]+-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])",
                            "Enter lower birth date as year-month-day", "Incorrect birth date");
                    String data1 = data = checkUntil("[0-9]+-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])",
                            "Enter upper birth date as year-month-day", "Incorrect birth date");
                    System.out.println(tourList.getTourLeaderList().findTourLeaderByBirthDateRange(
                            new Date(Integer.parseInt(data.split("-")[0]),
                                    Integer.parseInt(data.split("-")[1]),
                                    Integer.parseInt(data.split("-")[2])),
                            new Date(Integer.parseInt(data1.split("-")[0]),
                                    Integer.parseInt(data1.split("-")[1]),
                                    Integer.parseInt(data1.split("-")[2]))));
                    break;
            }
            System.out.println("Press Enter for continue");
            input.nextLine();
            back();
        }
    }

    public void tourMenu(){
        System.out.println("1- Show tour types");
        System.out.println("2- Show tours");
        System.out.println("3- Add tour type");
        System.out.println("4- Add tour");
        System.out.println("5- Edit tour type");
        System.out.println("6- Edit Tour");
        System.out.println("7- Remove tour");
        System.out.println("8- Find tour Type");
        System.out.println("9- Find Tour");
        System.out.println("10- back");

        checkInput(10);
    }

    public void showTourTypes(){
        tourList.showTourTypes();
        waitInput();
    }

    public void showTours(){
        tourList.showTours();
        waitInput();
    }

    public void addTourType(){
        TourContainer tourType = new TourContainer(makeTourType());
        back();
        tourList.addTourType(tourType);
    }

    public void addTour(){
        String data1, data2;
        Tour tour = new Tour();
        data1 = checkUntil("[a-zA-z][a-zA-z ]+", "Enter leader name", "Incorrect input");
        data2 = checkUntil("[a-zA-z][a-zA-z ]+", "Enter leader last name", "Incorrect input");
        if(tourList.getTourLeaderList().findSpecTourLeaderByFullName(data1, data2).equals(new TourLeader())) {
            System.out.println("There is any leader with this name");
            return;
        }
        tour.setTourLeader(tourList.getTourLeaderList().findSpecTourLeaderByFullName(data1, data2));
        tour.setTourId(Integer.parseInt(checkUntil("[1-9][0-9]+",
                "Enter Tour Id", "Incorrect input")));
        data1 = checkUntil("[0-9]+-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])",
                "Enter tour start date", "Incorrect input");
        tour.setStartDate(new Date(Integer.parseInt(data1.split("-")[0]),
                Integer.parseInt(data1.split("-")[1]),
                Integer.parseInt(data1.split("-")[2])));
        data1 = checkUntil("[a-zA-Z][a-zA-Z ]+:([a-zA-Z][a-zA-Z ]+|@[0-9]+)",
                "Enter Location of tour typr as locationName:coordinate",
                "Incorrect input");
        for(TourContainer i: tourList.findTourTypeByLocation(new Location(data1.split(":")[0], data1.split(":")[1]))){
            tour.setTourType(i);
            break;
        }
        back();
       tourList.addTour(tour);
    }

    private TourContainer makeTourType(){
        String data;
        TourContainer tourType = new TourContainer();
        tourType.setTourDuration(Integer.parseInt(checkUntil("[1-9]|[1-9][0-9]+",
                "Enter tour duration",
                "Incorrect input")));
        tourType.setTourPrice(Integer.parseInt(checkUntil("[1-9]|[1-9][0-9]+",
                "Enter tour price",
                "Incorrect input")));
        tourType.setMaxParticipants(Integer.parseInt(checkUntil("[1-9]|[1-9][0-9]+",
                "Enter tour Maximum participants",
                "Incorrect input")));
        tourType.setMinParticipants(Integer.parseInt(checkUntil("[0-9]|[1-9][0-9]+",
                "Enter tour Minimum participants",
                "Incorrect input")));
        data = checkUntil("[a-zA-Z][a-zA-Z ]+:([a-zA-Z][a-zA-Z ]+|@[0-9]+)",
                "Enter source location as locationName:coordinate",
                "Incorrect input");
        tourType.setSource(new Location(data.split(":")[0], data.split(":")[1]));
        data = checkUntil("[a-zA-Z][a-zA-Z ]+:([a-zA-Z][a-zA-Z ]+|@[0-9]+)",
                "Enter destination location as locationName:coordinate",
                "Incorrect input");
        tourType.setDestination(new Location(data.split(":")[0], data.split(":")[1]));
        data = checkUntil("Flight|Earth",
                "Enter way of travel as Flight or Earth",
                "Incorrect input");
        if(data.equals("Flight")){
            tourType.setWayOfTravel(WayOfTravel.FlightTrip);
        }
        else{
            tourType.setWayOfTravel(WayOfTravel.EarthTrip);
        }
        int dayCounter = 0;
        Set<Location>[] toDo = new Set[tourType.getTourDuration()];
        for(int i = 0; i < toDo.length; i++){
            toDo[i] = new HashSet<>();
        }
        while(dayCounter < tourType.getTourDuration()){
            data = checkUntil("([a-zA-Z][a-zA-Z ]+:([a-zA-Z][a-zA-Z ]+|@[0-9]+)|1)",
                    "Enter visiting locations as locationName:coordinate for day" + (dayCounter + 1) +
                            "\nIf you want to exit or go to next day press 1",
                    "Incorrect input");
            if(data.equals("1")){
                dayCounter++;
            }
            else{
                toDo[dayCounter].add(new Location(data.split(":")[0], data.split(":")[1]));
            }
        }
        tourType.setToDO(toDo);
        return tourType;
    }

    public void location(){
        String data;
        System.out.println("1- Show locations");
        System.out.println("2- add location");
        System.out.println("3- Edit location");
        System.out.println("4- Remove location");
        System.out.println("5- back");
        String buffer = checkInput(5);
        if(!buffer.equals("")){
            switch(buffer){
                case "1":
                    System.out.println(tourList.getLocation());
                    System.out.println("Press Enter for continue");
                    input.nextLine();
                    input.nextLine();
                    back();
                    break;
                case "2":
                    data = checkUntil("[a-zA-Z][a-zA-Z ]+:([a-zA-Z][a-zA-Z ]+|@[0-9]+)",
                            "Enter Location as locationName:coordinate",
                            "Incorrect input");
                    tourList.getLocation().addLocation(data.split(":")[0], data.split(":")[1]);
                    back();
                    break;
                case "3":
                    System.out.println("1- change location Name\n2- Change coordinate\n3- Back");
                    String buff = checkInput(3);
                    if(!buff.equals("")) {
                        switch (buff) {
                            case "1":
                                data = checkUntil("[a-zA-Z][a-zA-Z ]+|@[0-9]+",
                                        "Enter coordinate",
                                        "Incorrect input");
                                String data1 = checkUntil("[a-zA-Z][a-zA-Z ]+", "Enter new Location name",
                                        "Incorrect input");
                                tourList.getLocation().replaceName(data, data1);
                                back();
                                break;
                            case "2":
                                data = checkUntil("[a-zA-Z][a-zA-Z ]+",
                                        "Enter Location name",
                                        "Incorrect input");
                                String data2 = checkUntil("[a-zA-Z][a-zA-Z ]+|@[0-9]+", "Enter new coordinate",
                                        "Incorrect input");
                                tourList.getLocation().replaceCoordinate(data, data2);
                                back();
                                break;
                        }
                        back();
                    }
                    break;
                case "4":
                    data = checkUntil("[a-zA-Z][a-zA-Z ]+:([a-zA-Z][a-zA-Z ]+|@[0-9]+)",
                            "Enter Location as locationName:coordinate",
                            "Incorrect input");
                    tourList.getLocation().removeLocation(new Location(data.split(":")[0],
                            data.split(":")[1]));
                    back();
                    break;
            }
        }
    }

    public void showMap(){
        System.out.println("1- Show source");
        System.out.println("2- Show destination");
        System.out.println("3- Show source and destination");
        System.out.println("4- Current location");
        System.out.println("5- Show visiting sites");
        System.out.println("6- show Location");
        System.out.println("7- show to cities");
        System.out.println("8- Back");
        checkInput(8);
    }

    public void showSource(){
        Map map = new Map();
        map.showLocation(getTour().getTourType().getSource());
        back();
    }

    public void showDes(){
        Map map = new Map();
        map.showLocation(getTour().getTourType().getDestination());
        back();
    }

    public void showSourceDes(){
        Map map = new Map();
        map.showLocation(getTour().getTourType().getSource(), getTour().getTourType().getDestination());
        back();
    }

    public void showCurrentLocation(){
        String data;
        Map map = new Map();
        data = checkUntil("[0-9]+-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])",
                "Enter current Date", "Incorrect input");
        Date date = new Date(Integer.parseInt(data.split("-")[0]),
                Integer.parseInt(data.split("-")[1]),
                Integer.parseInt(data.split("-")[2]));
        map.showCurrent(getTour(), date);
        back();
    }

    public void showTourLocations(){
        Map map = new Map();
        map.showToDos(getTour());
        back();
    }

    public void showLocation(){
        Map map = new Map();
        String data;
        data = checkUntil("[a-zA-Z][a-zA-Z ]+:([a-zA-Z][a-zA-Z ]+|@[0-9]+)",
                "Enter location as locationName:coordinate", "Incorrect input");
        Location location = new Location(data.split(":")[0], data.split(":")[1]);
        if(!tourList.getLocation().contains(location)){
            System.out.println("There is any location like this in location list");
            waitInput();
            return;
        }
        map.showLocation(location);
        back();
    }

    public void twoLocation(){
        Map map = new Map();
        String data;
        data = checkUntil("[a-zA-Z][a-zA-Z ]+:([a-zA-Z][a-zA-Z ]+|@[0-9]+)",
                "Enter first location as locationName:coordinate", "Incorrect input");
        Location location1 = new Location(data.split(":")[0], data.split(":")[1]);
        if(!tourList.getLocation().contains(location1)){
            System.out.println("There is any location like this in location list");
            waitInput();
            return;
        }
        data = checkUntil("[a-zA-Z][a-zA-Z ]+:([a-zA-Z][a-zA-Z ]+|@[0-9]+)",
                "Enter second location as locationName:coordinate", "Incorrect input");
        Location location2 = new Location(data.split(":")[0], data.split(":")[1]);
        if(!tourList.getLocation().contains(location2)){
            System.out.println("There is any location like this in location list");
            waitInput();
            return;
        }
        map.showLocation(location1, location2);
        back();
    }

    public void back(){
        if(pointer.length() > 1) {
            pointer = pointer.substring(0, pointer.length() - 1);
            clearScreen();
        }
    }

    private void waitInput(){
        System.out.println("Press Enter for continue");
        input.nextLine();
        input.nextLine();
        back();
    }

    public String checkUntil(String regex, String message, String err){
        String result;
        System.out.println(message);
        result = input.nextLine();
        if(result.length() == 0){
            result = input.nextLine();
        }
        while(!result.matches(regex)){
            System.out.println(err);
            result = input.nextLine();
        }
        clearScreen();
        return result;
    }

    public String checkInput(int upperRange){
        String buffer = input.next();
        if(upperRange >= 9){
            for(int i = 1; i < upperRange + 1; i++) {
                if(!(buffer.compareTo(Integer.toString(upperRange)) <= upperRange - 1 && buffer.compareTo("1") >= 0)){
                    clearScreen();
                    System.out.println(buffer.compareTo(Integer.toString(upperRange)));
                    System.out.println(buffer.compareTo(Integer.toString(1)));
                    System.out.println("Incorrect input.");
                    return "";
                }
            }
        }
        else {
            if (!buffer.matches("[1-" + upperRange + "]")) {
                clearScreen();
                System.out.println("Incorrect input.");
                return "";
            }
        }

        if(buffer.equals(Integer.toString(upperRange))){
            back();
            return "";
        }

        pointer += buffer;
        clearScreen();
        return buffer;
    }

    private Tour getTour(){
        String data1, data2;
        data1 = checkUntil("[a-zA-Z][a-zA-Z ]+:([a-zA-Z][a-zA-Z ]+|@[0-9]+)",
                "Enter tour destination as locationName:coordinate",
                "Incorrect input");
        data2 = checkUntil("[1-9]|[1-9][0-9]+", "Enter tour Id", "Incorrect input");
        if(tourList.findTour(new Location(data1.split(":")[0], data1.split(":")[1]),
                Integer.parseInt(data2)).equals(new Tour())){
            System.out.println("There is any location like this in location list");
            waitInput();
            return new Tour();
        }
        return tourList.findTour(new Location(data1.split(":")[0], data1.split(":")[1]),
                Integer.parseInt(data2));
    }

    public void showMenu(){
        switch (pointer){
            case "1":
                showDefault();
                break;
            case "11":
                showLeaders();
                break;
            case "111":
                showLeaderList();
                break;
            case "112":
                addLeader();
                break;
            case "113":
                removeLeader();
                break;
            case "114":
                editLeader();
                break;
            case "115":
                findLeader();
                break;
            case "12":
                tourMenu();
                break;
            case "121":
                showTourTypes();
                break;
            case "122":
                showTours();
                break;
            case "123":
                addTourType();
                break;
            case "124":
                addTour();
                break;

            case "13":
                location();
                break;
            case "14":
                showMap();
                break;
            case "141":
                showSource();
                break;
            case "142":
                showDes();
                break;
            case "143":
                showSourceDes();
                break;
            case "144":
                showCurrentLocation();
                break;
            case "145":
                showTourLocations();
                break;
            case "146":
                showLocation();
                break;
            case "147":
                twoLocation();
                break;
        }
    }

    public void clearScreen(){
        for(int i = 0; i < 50; i++){
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        while(true) {
            try {
                menu.showMenu();
            }
            catch (Exception e){
                menu.clearScreen();
                System.out.println("THere is an error please try again.");
                menu.setDefault();
            }
        }
    }
}
