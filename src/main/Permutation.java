import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Permutation {
    private final Driver target;
    private final List<SimDriver> standings;

    public Permutation(Driver target, List<SimDriver> standings) {
        this.target = target;
        this.standings = new ArrayList<>(standings); // defensive copy
    }

    public Driver getTarget() {
        return target;
    }

    public List<SimDriver> getStandings() {
        return Collections.unmodifiableList(standings);
    }

    @Override
    public String toString() {
        return standings.stream()
                .map(sd -> sd.getDriver().getName() + " P" + sd.getPosition())
                .collect(Collectors.joining(" | "));
    }
}