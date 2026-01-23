import java.util.Map;

public class Race {
    /*
    private static final String[] CALENDAR_2025 = {"australia", "china", "japan", "bahrain", "saudiArabia", "miami", "emiliaRomagna", "monaco",
                                                    "spain", "canada", "australian", "britain", "belgium", "hungary", "netherlands", "italy",
                                                    "singapore", "unitedStates", "mexico", "brazil", "lasVegas", "qatar", "abuDhabi"};

    private String location;
     */

    private String race;
    private Map<Integer, String> results;

    public Race() {
    }

    // getters
    public Map<Integer, String> getResults() {
        return this.results;
    }

    public String getDriver(int p) {
        return results.get(p);
    }

    public void setRace(String race) {
        this.race = race;

    /*
    // setters

    }
    */

    }
}
