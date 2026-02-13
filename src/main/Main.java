import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static void main(String[] args) throws Exception {

        // initialize lineup from AbuDhabi.json (creates drivers)
        Race abuDhabi = readJson("AbuDhabi.json", Race.class);
        RaceProcessor.initializeLineup(abuDhabi);

        // apply pre-race points snapshot
        Race pointsBefore = readJson("pointsBeforeAbuDhabi.json", Race.class);
        RaceProcessor.applyPointsSnapshot(pointsBefore);

        // print contenders so user knows what to type
        Driver[] contenders = Drivers2025.getContenders();
        System.out.println("Title contenders:");
        for (Driver d : contenders) {
            System.out.println("- " + d.getName() + " (" + d.getPoints() + " pts)");
        }

        // user chooses contender by FIRST name only
        Scanner sc = new Scanner(System.in);
        System.out.println("\nInput first name of driver for their championship permutations:");
        String choice = sc.nextLine().trim().toLowerCase(Locale.ROOT);

        Driver target = pickContenderByFirstName(choice);
        if (target == null) {
            System.out.println("Could not match that first name to a contender.");
            return;
        }

        // permute + print
        Permutor permutor = new Permutor(target);
        permutor.permute();

        System.out.println("\n" + target.getName() + " permutations: " + target.getPermutations().size());
        for (Permutation p : target.getPermutations()) {
            System.out.println(p);
        }
    }

    private static Driver pickContenderByFirstName(String firstNameLower) {
        if (firstNameLower == null) return null;
        firstNameLower = firstNameLower.trim();
        if (firstNameLower.isEmpty()) return null;

        Driver[] contenders = Drivers2025.getContenders();
        Driver found = null;

        for (Driver d : contenders) {
            String name = d.getName();
            if (name == null) continue;

            String[] parts = name.trim().split("\\s+");
            if (parts.length == 0) continue;

            String first = parts[0].toLowerCase(Locale.ROOT);

            if (first.equals(firstNameLower)) {
                if (found != null) return null; // ambiguous first name
                found = d;
            }
        }
        return found;
    }

    private static <T> T readJson(String filename, Class<T> clazz) throws Exception {
        try (InputStream in = Main.class.getClassLoader().getResourceAsStream(filename)) {
            if (in != null) return MAPPER.readValue(in, clazz);
        }
        try (InputStream in = new FileInputStream(filename)) {
            return MAPPER.readValue(in, clazz);
        }
    }
}