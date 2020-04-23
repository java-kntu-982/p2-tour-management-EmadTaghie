package ir.ac.kntu;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Menu {
    private Scanner input;
    private String pointer;
    private String curUser;
    private TourManagement tourManagement;
    private Map map;
    private java.util.Map<String, User> users;

    private final String nameRegex = "[a-zA-Z][a-zA-Z ]+";
    private final String idRegex = "[1-9][0-9]+";
    private final String dateRegex = "[0-9]+-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])";
    private final String locationRegex = "[a-zA-Z][a-zA-Z ]+:([a-zA-Z][a-zA-Z ]+|@[0-9]+)";
    private final String userRegex = "[a-zA-Z0-9 ]+";


    public Menu(){
        input = new Scanner(System.in);
        pointer = "2";
        tourManagement = new TourManagement();
        map = new Map();
        users = new HashMap<>();
        curUser = "gust";
        users.put(curUser, new User());
        users.put("Admin", new User("Admin", "1111",
                "exmpl@gmail.com", "09111111111", UserState.Admin));
    }

    public void setDefault(){
        pointer = "1";
    }
    public void setDefaultCustomer(){
        pointer = "2";
    }

    private void showDefault() {
        System.out.println("Enter the number of option you want to see");
        System.out.println("1- Leaders");
        System.out.println("2- Tours");
        System.out.println("3- Locations");
        System.out.println("4- Maps");
        System.out.println("5- User settings");
        System.out.println("6- Edit user");
        checkInput(7);
    }

    private void showDefaultCostumer() {
        System.out.println("Enter the number of option you want to see");
        System.out.println("1- Leaders");
        System.out.println("2- Tours");
        System.out.println("3- Locations");
        System.out.println("4- Maps");
        System.out.println("5- Edit user");
        System.out.println("6- Change user");
        checkInput(7);
    }

    private void leaderMenu(){
        System.out.println("1- Show leaders");
        System.out.println("2- Add leader");
        System.out.println("3- Remove leader");
        System.out.println("4- Edit leader");
        System.out.println("5- FindLeader");
        System.out.println("6- Back");
        checkInput(6);
    }

    private void leaderMenuCostumer(){
        System.out.println("1- Show leaders");
        System.out.println("2- FindLeader");
        System.out.println("3- Back");
        checkInput(3);
    }

    private void showLeaderList(){
        System.out.println(tourManagement.getTourLeaderList().getTourLeaders().size() == 0 ?
                "There is any tourLeader in the list.": tourManagement.getTourLeaderList().getTourLeaders().toString());
        waitInput();
    }

    private void addLeader(){
        TourLeader tourLeader = new TourLeader();
        String data;
        tourLeader.setName(checkUntil(nameRegex, "Enter name"));
        tourLeader.setLastName(checkUntil(nameRegex, "Enter last name"));
        tourLeader.setNationalId(checkUntil(idRegex, "Enter national Id"));
        tourLeader.setBirthCertId(checkUntil(idRegex, "Enter birth certificate Id"));
        data = checkUntil(dateRegex, "Enter birth date as year-month-day");
        tourLeader.setBirthDate(new Date(splitDate(data)[0], splitDate(data)[1], splitDate(data)[2]));
        data = checkUntil(dateRegex, "Enter hire date as year-month-day");
        tourLeader.setHireDate(new Date(splitDate(data)[0], splitDate(data)[1], splitDate(data)[2]));
        System.out.println("Enter acquainted locations");
        System.out.println("press 1 if you want to exit");
        while(!data.equals("1")){
            data = input.nextLine();
            if(data.matches(locationRegex)){
                tourLeader.addAcquaintedLocations(data.split(":")[0], data.split(":")[1]);
            }
            else if(!data.equals("1")){
                System.out.println("Incorrect location");
            }
        }
        tourManagement.addLeader(tourLeader);
        waitInput();
    }

    private void removeLeader(){
        tourManagement.getTourLeaderList().removeTourLeader(checkUntil(nameRegex,"Enter leader name"),
                checkUntil(nameRegex,"Enter leader last name"));
        back();
    }

    private void editLeader(){
        TourLeader tourLeader = tourManagement.getTourLeaderList().findSpecTourLeaderByFullName(
                checkUntil(nameRegex, "Enter leader name"),
                checkUntil(nameRegex, "Enter leader last name"));
        if(tourLeader.equals(new TourLeader())){
            System.out.println("Can not find the leader");
            waitInput();
            return;
        }
        editBar(tourLeader);
    }

    private void editBar(TourLeader tourLeader) {
        System.out.println("1- Change name");
        System.out.println("2- Change last name");
        System.out.println("3- Change national Id");
        System.out.println("4- Change birth certificate Id");
        System.out.println("5- Change birth date");
        System.out.println("6- Change hire date");
        System.out.println("7- Change acquainted locations");
        System.out.println("8- Back");
        checkInput(8);
        if(pointer.matches("[1-9]14[1-7]+")) {
            editLeaderOption(tourLeader);
        }
    }

    private void editLeaderOption(TourLeader tourLeader) {
        switch(pointer.substring(pointer.length() - 1)){
            case "1":
                tourLeader.setName(checkUntil(nameRegex, "Enter leader name"));
                break;
            case "2":
                tourLeader.setLastName(checkUntil(nameRegex, "Enter leader last name"));
                break;
            case "3":
                tourLeader.setNationalId(checkUntil(idRegex, "Enter leader national Id"));
                break;
            case "4":
                tourLeader.setBirthCertId(checkUntil(idRegex, "Enter leader birth certificate Id"));
                break;
            case "5":
                tourLeader.setBirthDate(getDate("Enter birth date as year-month-day"));
                break;
            case "6":
                tourLeader.setHireDate(getDate("Enter hire date as year-month-day"));
                break;
            case "7":
                tourLeader.getAcquaintedLocations().removeLocation(getLocation("Enter Location as locationName:coordinate"));
                tourLeader.getAcquaintedLocations().addLocation(getLocation("Enter new Location as locationName:coordinate"));
                break;
            }
        waitInput();
        back();
    }

    private void findLeader(){
        System.out.println("1- Find leader by name");
        System.out.println("2- Find leader by last name");
        System.out.println("3- Find leader by acquainted location");
        System.out.println("4- Find leader by birth date");
        System.out.println("5- Find leader by older than specific date");
        System.out.println("6- find leader by younger than specific date");
        System.out.println("7-find leader by between date");
        System.out.println("8- back");
        findLeaderOption(checkInput(8));
    }

    private void findLeaderOption(@NotNull String option) {
        if(!option.equals("")){
            switch(option){
                case "1":
                    System.out.println(tourManagement.getTourLeaderList().findTourLeaderByName(
                            checkUntil(nameRegex, "Enter leader name")));
                    break;
                case "2":
                    System.out.println(tourManagement.getTourLeaderList().findTourLeaderByLastName(
                            checkUntil(nameRegex, "Enter leader last name")));
                    break;
                case "3":
                    System.out.println(tourManagement.getTourLeaderList().findTourLeaderByLocation(
                            new Location(getLocation("Enter Location as locationName:coordinate"))));
                    break;
                case "4":
                    System.out.println(tourManagement.getTourLeaderList().
                            findTourLeaderByDateOfBirth(getDate("Enter birth date as year-month-day")));
                    break;
                case "5":
                    System.out.println(tourManagement.getTourLeaderList().findTourLeaderByLowerDateOfBirth(
                            getDate("Enter birth date as year-month-day")));
                    break;
                case "6":
                    System.out.println(tourManagement.getTourLeaderList().findTourLeaderByUpperDateOfBirth(
                            getDate("Enter birth date as year-month-day")));
                    break;
                case "7":
                    System.out.println(tourManagement.getTourLeaderList().findTourLeaderByBirthDateRange(
                            getDate("Enter lower birth date as year-month-day"),
                            getDate("Enter upper birth date as year-month-day")));
                    break;
            }
            waitInput();
        }
    }

    private void tourMenu(){
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

    private void tourMenuCostumer(){
        System.out.println("1- Show tour types");
        System.out.println("2- Show tours");
        System.out.println("3- Find tour Type");
        System.out.println("4- Find Tour");
        System.out.println("5- back");

        checkInput(5);
    }

    private void showTourTypes(){
        tourManagement.getTourTypeList().showTourTypes();
        waitInput();
    }

    private void showTours(){
        tourManagement.getTourList().showTours();
        waitInput();
    }

    private void addTourType(){
        TourType tourType = new TourType(makeTourType());
        back();
        tourManagement.addTourType(tourType);
    }

    private void addTour(){
        String data1, data2;
        Tour tour = new Tour();
        data1 = checkUntil(nameRegex, "Enter leader name");
        data2 = checkUntil(nameRegex, "Enter leader last name");
        if(tourManagement.getTourLeaderList().findSpecTourLeaderByFullName(data1, data2).equals(new TourLeader())) {
            System.out.println("There is any leader with this name");
            return;
        }
        tour.setTourLeader(tourManagement.getTourLeaderList().findSpecTourLeaderByFullName(data1, data2));
        tour.setTourId(Integer.parseInt(checkUntil(idRegex, "Enter Tour Id")));
        tour.setStartDate(getDate("Enter tour start date"));
        for(TourType i: tourManagement.getTourTypeList().findTourTypeByLocation(getLocation
                ("Enter Location of tour type as locationName:coordinate"))){
            tour.setTourType(i);
            break;
        }
        back();
       tourManagement.addTour(tour);
    }

    @NotNull
    private TourType makeTourType(){
        String durationRegex = "[1-9]|[1-9][0-9]+";
        String data;
        TourType tourType = new TourType();
        tourType.setTourDuration(Integer.parseInt(checkUntil(durationRegex, "Enter tour duration")));
        tourType.setTourPrice(Integer.parseInt(checkUntil(durationRegex, "Enter tour price")));
        tourType.setMaxParticipants(Integer.parseInt(checkUntil(durationRegex, "Enter tour Maximum participants")));
        tourType.setMinParticipants(Integer.parseInt(checkUntil(durationRegex, "Enter tour Minimum participants")));
        tourType.setSource(getLocation("Enter source location as locationName:coordinate"));
        tourType.setDestination(getLocation("Enter destination location as locationName:coordinate"));
        data = checkUntil("Flight|Earth", "Enter way of travel as Flight or Earth");
        tourType.setWayOfTravel(data.equals("Flight") ? WayOfTravel.FlightTrip: WayOfTravel.EarthTrip);
        tourType.setToDO(getToDoes(tourType));
        return tourType;
    }

    @NotNull
    private Set<Location>[] getToDoes(@NotNull TourType tourType) {
        String data;
        int dayCounter = 0;
        Set<Location>[] toDo = new Set[tourType.getTourDuration()];
        for(int i = 0; i < toDo.length; i++){
            toDo[i] = new HashSet<>();
        }
        while(dayCounter < tourType.getTourDuration()){
            data = checkUntil(locationRegex,
                    "Enter visiting locations as locationName:coordinate for day" + (dayCounter + 1) +
                            "\nIf you want to exit or go to next day press 1");
            if(data.equals("1")){
                dayCounter++;
            }
            else{
                toDo[dayCounter].add(new Location(data.split(":")[0], data.split(":")[1]));
            }
        }
        return toDo;
    }

    private void location(){
        System.out.println("1- Show locations");
        System.out.println("2- add location");
        System.out.println("3- Edit location");
        System.out.println("4- Remove location");
        System.out.println("5- back");
        locationOption(checkInput(5));
    }

    private void locationCostumer(){
        System.out.println("1- Show locations");
        System.out.println("2- back");
        locationOption(checkInput(2));
    }

    private void locationOption(@NotNull String option) {
        if(!option.equals("")){
            switch(option){
                case "1":
                    System.out.println(tourManagement.getLocation());
                    waitInput();
                    break;
                case "2":
                    tourManagement.getLocation().addLocation(getLocation("Enter Location as locationName:coordinate"));
                    back();
                    break;
                case "3":
                    System.out.println("1- change location Name\n2- Change coordinate\n3- Back");
                    editLocationBar(checkInput(3));
                    break;
                case "4":
                    tourManagement.getLocation().removeLocation(getLocation("Enter Location as locationName:coordinate"));
                    back();
                    break;
            }
        }
    }

    private void editLocationBar(@NotNull String buff) {
        if(!buff.equals("")) {
            String coordinateRegex = "[a-zA-Z][a-zA-Z ]+|@[0-9]+";
            switch (buff) {
                case "1":
                    tourManagement.getLocation().replaceName(
                            checkUntil(coordinateRegex, "Enter coordinate"),
                            checkUntil(nameRegex, "Enter new Location name"));
                    back();
                    break;
                case "2":
                    tourManagement.getLocation().replaceCoordinate(
                            checkUntil(nameRegex, "Enter Location name"),
                            checkUntil(coordinateRegex, "Enter new coordinate"));
                    back();
                    break;
            }
            back();
        }
    }

    private void showMap(){
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

    private void showSource(){
        map.showLocation(getTour().getSource());
        back();
    }

    private void showDes(){
        map.showLocation(getTour().getDestination());
        back();
    }

    private void showSourceDes(){
        map.showLocation(getTour().getSource(), getTour().getDestination());
        back();
    }

    private void showCurrentLocation(){
        map.showCurrent(getTour(), getDate("Enter current Date"));
        back();
    }

    private void showTourLocations(){
        map.showToDos(getTour());
        back();
    }

    private void showLocation(){
        Location location = getLocation("Enter location as locationName:coordinate");
        if(!tourManagement.getLocation().contains(location)){
            System.out.println("There is any location like this in location list");
            waitInput();
            return;
        }
        map.showLocation(location);
        back();
    }

    private void twoLocation(){
        Location location1 = getLocation("Enter first location as locationName:coordinate");
        if(!tourManagement.getLocation().contains(location1)){
            System.out.println("There is any location like this in location list");
            waitInput();
            return;
        }
        Location location2 = new Location(getLocation("Enter second location as locationName:coordinate"));
        if(!tourManagement.getLocation().contains(location2)){
            System.out.println("There is any location like this in location list");
            waitInput();
            return;
        }
        map.showLocation(location1, location2);
        back();
    }

    private void changeUser(){
        String userName = checkUntil(userRegex, "Enter your username");
        if(users.containsKey(userName)){
            curUser = userName;
            clearScreen();
            changePointer();
            return;
        }
        System.out.println("Incorrect username.");
        clearScreen();
    }

    private void changePointer(){
        if(users.get(curUser).getUserState().equals(UserState.Costumer)){
            pointer = "2";
        }
        else if(users.get(curUser).getUserState().equals(UserState.Admin)){
            pointer = "1";
        }
    }

    private void userSettings(){
        System.out.println("1- Show users");
        System.out.println("2- Add user");
        System.out.println("3- Remove user");
        System.out.println("4- Edit leader");
        System.out.println("5- FindLeader");
        System.out.println("6- Back");
        checkInput(6);
    }

    private void editUserMenu(){
        System.out.println("1- Show costumers");
        System.out.println("2- Change username");
        System.out.println("3- Chane Password");
        System.out.println("4- Change email");
        System.out.println("5- Change phone number");
        System.out.println("6- Back");
        switch (checkInput(6)){
            case "1":
                System.out.println(users.get(curUser));
                waitInput();
                break;
            case "2":
                users.get(curUser).setUserName(checkUntil(userRegex, "Enter new username"));
                back();
                break;
            case "3":
                users.get(curUser).setPassword(checkUntil("[0-9a-zA-z]+", "Enter new password"));
                back();
                break;
            case "4":
                users.get(curUser).setEmail(checkUntil("[0-9a-zA-Z.]+@[a-z]+.com", "Enter new email"));
                back();
                break;
            case "5":
                users.get(curUser).setPhoneNum(checkUntil("(09|\\+989)[1-9][0-9]{8}", "Enter new phone number"));
                back();
                break;
        }
    }

    @NotNull
    private int[] splitDate(String date){
        int[] splitDate = new int[3];
        for(int i = 0; i < 3; i++) {
            splitDate[i] = Integer.parseInt(date.split("-")[i]);
        }
        return splitDate;
    }

    @NotNull
    private Date getDate(String message) {
        String buffer = checkUntil(dateRegex, message);
        return new Date(splitDate(buffer)[0], splitDate(buffer)[1], splitDate(buffer)[2]);
    }

    @NotNull
    private Location getLocation(String message) {
        String[] buffer = checkUntil(locationRegex, message).split(":");
        return new Location(buffer[0], buffer[1]);
    }

    private void back(){
        if(pointer.length() > 1) {
            pointer = pointer.substring(0, pointer.length() - 1);
        }
        clearScreen();
    }

    private void waitInput(){
        System.out.println("Press Enter for continue");
        input.nextLine();
        input.nextLine();
        back();
    }

    private String checkUntil(String regex, String message){
        String result;
        System.out.println(message);
        result = input.nextLine();
        if(result.length() == 0){
            result = input.nextLine();
        }
        while(!result.matches(regex)){
            System.out.println("Incorrect input.");
            result = input.nextLine();
        }
        clearScreen();
        return result;
    }

    @NotNull
    private String checkInput(int upperRange){
        String buffer = input.next();
        if(!checkRange(upperRange, buffer)) return "";

        if(buffer.equals(Integer.toString(upperRange))){
            back();
            return "";
        }

        pointer += buffer;
        clearScreen();
        return buffer;
    }

    private boolean checkRange(int upperRange, String buffer) {
        if(upperRange >= 9){
            for(int i = 1; i < upperRange + 1; i++) {
                if(!(buffer.compareTo(Integer.toString(upperRange)) <= upperRange - 1 && buffer.compareTo("1") >= 0)){
                    clearScreen();
                    System.out.println(buffer.compareTo(Integer.toString(upperRange)));
                    System.out.println(buffer.compareTo(Integer.toString(1)));
                    System.out.println("Incorrect input.");
                    return false;
                }
            }
        }
        else {
            if (!buffer.matches("[1-" + upperRange + "]")) {
                clearScreen();
                System.out.println("Incorrect input.");
                return false;
            }
        }
        return true;
    }

    private Tour getTour(){
        Location location = getLocation("Enter tour destination as locationName:coordinate");
        int id = Integer.parseInt(checkUntil(idRegex, "Enter tour Id"));
        if(tourManagement.getTourList().findTour(location, id).equals(new Tour())){
            System.out.println("There is any location like this in location list");
            waitInput();
            return new Tour();
        }
        return tourManagement.getTourList().findTour(location, id);
    }

    public void showMenu(){
        if(users.get(curUser).getUserState().equals(UserState.Admin)) {
            defaultMenuOption();
        }
        else if(users.get(curUser).getUserState().equals(UserState.Costumer)){
            costumerMenuOption();
        }
    }

    private void defaultMenuOption() {
        switch (pointer){
            case "1":
                showDefault();
                break;
            case "11":
                leaderMenu();
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
            default:
                System.out.println("Incorrect input");
                setDefault();
                break;
        }
    }

    private void costumerMenuOption() {
        switch (pointer){
            case "2":
                showDefaultCostumer();
                break;
            case "21":
                leaderMenuCostumer();
                break;
            case "211":
                showLeaderList();
                break;
            case "212":
                findLeader();
                break;
            case "22":
                tourMenuCostumer();
                break;
            case "221":
                showTourTypes();
                break;
            case "222":
                showTours();
                break;

            case "23":
                locationCostumer();
                break;
            case "24":
                showMap();
                break;
            case "241":
                showSource();
                break;
            case "242":
                showDes();
                break;
            case "243":
                showSourceDes();
                break;
            case "244":
                showCurrentLocation();
                break;
            case "245":
                showTourLocations();
                break;
            case "246":
                showLocation();
                break;
            case "247":
                twoLocation();
                break;
            case "25":
                editUserMenu();
                break;
            case "26":
                changeUser();
                break;
            default:
                System.out.println("Incorrect input");
                setDefaultCustomer();
                break;
        }
    }

    public void clearScreen(){
        for(int i = 0; i < 50; i++){
            System.out.println();
        }
    }
}
