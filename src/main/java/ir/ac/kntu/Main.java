package ir.ac.kntu;


import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        while(true) {
            try {
                menu.showMenu();
            }
            catch (Exception e){
                System.out.println("Incorrect input.");
                menu.setDefault();
            }
        }
    }
}
