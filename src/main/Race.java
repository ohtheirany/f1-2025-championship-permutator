import java.util.Map;

public class Race {
    private String race;

    // race result files like AbuDhabi.json
    private Map<Integer, String> results;

    // points snapshot file like pointsBeforeAbuDhabi.json
    private Map<String, Integer> points;

    public Race() {}

    public String getRace() { return race; }
    public void setRace(String race) { this.race = race; }

    public Map<Integer, String> getResults() { return results; }
    public void setResults(Map<Integer, String> results) { this.results = results; }

    public Map<String, Integer> getPoints() { return points; }
    public void setPoints(Map<String, Integer> points) { this.points = points; }
}