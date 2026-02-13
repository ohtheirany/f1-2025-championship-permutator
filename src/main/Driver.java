import java.util.ArrayList;
import java.util.List;

public class Driver {
    private String name;
    private int points;

    private final List<Permutation> permutations = new ArrayList<>();

    public Driver() {}

    public Driver(String name) {
        this.name = name;
        this.points = 0;
    }

    public Driver(String name, int points) {
        this.name = name;
        this.points = points;
    }

    public String getName() { return this.name; }
    public int getPoints() { return this.points; }
    public List<Permutation> getPermutations() { return this.permutations; }

    public void setName(String name) { this.name = name; }
    public void setPoints(int points) { this.points = points; }
    public void addPoints(int points) { this.points += points; }

    public void addPermutation(Permutation permutation) {
        this.permutations.add(permutation);
    }

    public boolean morePointsThan(Driver other) {
        return this.points > other.points;
    }
}