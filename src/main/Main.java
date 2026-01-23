import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;

public class Main {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static void main(String[] args) throws Exception {
        try (InputStream in = Main.class.getClassLoader().getResourceAsStream("AbuDhabi.json")) {
            if (in == null) throw new IllegalStateException("AbuDhabi.json not found in src/main/resources");

            Race abuDhabi = MAPPER.readValue(in, Race.class);
            processNewRace(abuDhabi);

            System.out.println(Drivers2025.getStandings().get(0).getName());
        }
    }

    private static void processNewRace(Race race){
        RaceProcessor.processRace(race);
    }
}