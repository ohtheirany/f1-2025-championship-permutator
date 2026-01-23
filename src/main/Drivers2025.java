import java.util.ArrayList;
import java.util.Collections;

/*
    This class acts as a Hash containing all 2025 drivers and is always in order of the current standings in the 2025 World Drivers Championship
 */

public class Drivers2025 {

    private static final ArrayList<Driver> STANDINGS = new ArrayList<>();
    private static int driverTotal;

    public Drivers2025(){
    }

    // setters
    public static int setDriverTotal(int total){
        return driverTotal = total;
    }

    // getters
    public static int getDriverTotal(){
        return driverTotal;
    }

    public static ArrayList<Driver> getStandings(){
        return STANDINGS;
    }

    // add driver
    public static void addDriver (Driver driver) {
        // raceProcessor's ___ method knows the position
        STANDINGS.add(driver);
        driverTotal += 1;

        // to add — if driver points = 0, add to the end of the ArrayList
    }

    //sort
    public static void sortAllStandings(){
        int maxIndex;
        for (int i = 0; i < STANDINGS.size(); i++){
            maxIndex = i;
            for (int j = i+1; j < STANDINGS.size(); j++){
                if (STANDINGS.get(j).morePointsThan(STANDINGS.get(i))){
                    maxIndex = j;
                }
            }
            if (i != maxIndex){
                Collections.swap(STANDINGS, i, maxIndex);
            }
        }
    }

    //driverExists
    public static boolean driverExists(String driverName){
        for (Driver driver: STANDINGS){
            if (driver.getName().equals(driverName)){
                return true;
            }
        }
        return false;
    }
}
