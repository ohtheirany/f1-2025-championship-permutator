import java.util.Map;
import java.util.Set;

public class RaceProcessor {

    public static final int[] POINTS_AWARDED = { 25,18,15,12,10,8,6,4,2,1 };

    public static void initializeLineup(Race race){
        for (Map.Entry<Integer, String> entry: race.getResults().entrySet()){
            String driverName = entry.getValue();
            createAndAddDriver(driverName);
        }
    }

    public static void applyPointsSnapshot(Race race) {
        if (race == null || race.getPoints() == null) {
            throw new IllegalArgumentException("Points snapshot JSON must contain a 'points' object map.");
        }

        for (Driver d : Drivers2025.getStandings()) {
            Integer pts = race.getPoints().get(d.getName());
            if (pts != null) d.setPoints(pts);
        }
        Drivers2025.sortAllStandings();
    }

    public static void createAndAddDriver(String driverName){
        Driver newDriver = new Driver(driverName);
        Drivers2025.addDriver(newDriver);
    }

    public static int awardPoints(int position){
        if (position < 1 || position > 10) return 0;
        return POINTS_AWARDED[position - 1];
    }

    public static void awardTop10 (Race race){
        Set<Map.Entry<Integer, String>> entries = race.getResults().entrySet();
        for (Map.Entry<Integer, String> entry: entries){
            int points = awardPoints(entry.getKey());
            String driverName = entry.getValue();
            for (int i = 0; i < Drivers2025.getDriverTotal(); i++){
                if (driverName.equals(Drivers2025.getStandings().get(i).getName())){
                    Drivers2025.getStandings().get(i).addPoints(points);
                }
            }
        }
    }
}