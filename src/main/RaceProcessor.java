import java.util.Map;
import java.util.Set;

public class RaceProcessor {

    public static final int[] POINTS_AWARDED = {
            25, 18, 15, 12, 10, 8, 6, 4, 2, 1
    };

    public static void processRace(Race race) {
        // if this is the first race being processed, initialize driver lineup and exit
        if (Drivers2025.getDriverTotal() == 0) {
            initializeLineup(race);
            awardTop10(race);
            Drivers2025.sortAllStandings();
            return;
        }

        // if this is not the first race, check if any drivers are new and create them as new Driver objects
        driversCheck(race);

        // award points to the top 10 finishers
        awardTop10(race);
        Drivers2025.sortAllStandings();
    }

    public static void initializeLineup(Race race){
        // initializes the 2025 driver lineup off of first calendar race results (assumption) without adding points to drivers
            for (Map.Entry<Integer, String> entry: race.getResults().entrySet()){
                String driverName = entry.getValue();
                createAndAddDriver(driverName);
            }
    }

    public static void driversCheck(Race race){
        String driverName;
        Set<Map.Entry<Integer, String>> entries = race.getResults().entrySet();
        for (Map.Entry<Integer, String> entry: entries){
            driverName = entry.getValue();
            if (!Drivers2025.driverExists(driverName)){
                // SHOULD create new Drivers without including points won because awardTop10 in processRace WILL award points
                createAndAddDriver(driverName);
            }
        }
    }

    public static void printNewDriver(Driver driver){
        System.out.println("New driver: " + driver.getName());
    }

    public static void createAndAddDriver(String driverName){
        // creates a new Driver object (without points) for a driver who didn't previously exist
        Driver newDriver = new Driver(driverName);
        Drivers2025.addDriver(newDriver);
        printNewDriver(newDriver);
    }

    public static int awardPoints(int position){
        int points;
        if (position <= 10){
            points = POINTS_AWARDED[position - 1];
        }
        else points = 0;

        return points;
    }

    public static void awardTop10 (Race race){
        Set<Map.Entry<Integer, String>> entries = race.getResults().entrySet();
        String driverName;
        int points;
        for (Map.Entry<Integer, String> entry: entries){
            points = awardPoints(entry.getKey());
            driverName = entry.getValue();
            for (int i = 0; i < Drivers2025.getDriverTotal(); i++){
                if (driverName.equals(Drivers2025.getStandings().get(i).getName())){
                    // add points to Driver object
                    Drivers2025.getStandings().get(i).addPoints(points);
                }
            }
        }
    }
}


