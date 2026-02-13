import java.util.ArrayList;

public class Permutor {

    private final Driver targetDriver;
    private Driver[] rivals;

    private final ArrayList<SimDriver> simStandings = new ArrayList<>();
    private final ArrayList<Integer> usedPositions = new ArrayList<>();

    public Permutor(Driver targetDriver) {
        this.targetDriver = targetDriver;
    }

    public void permute() {
        targetDriver.getPermutations().clear();

        rivals = getRivals(targetDriver);
        if (rivals.length == 0) return;

        // P1..P10 score points; P11 represents "11th or worse" (0 points)
        for (int mainPos = 11; mainPos >= 1; mainPos--) {

            simStandings.clear();
            usedPositions.clear();

            SimDriver mainSim = new SimDriver(targetDriver);
            mainSim.setPosition(mainPos);

            simStandings.add(mainSim);
            usedPositions.add(mainPos);

            // Now assign ALL rivals, however many there are
            assignRivalPositions(0, mainSim);

            // (loop continues to next mainPos)
        }
    }

    /**
     * Assigns positions to rivals[rivalIndex], rivals[rivalIndex+1], ... until done.
     */
    private void assignRivalPositions(int rivalIndex, SimDriver mainSim) {
        // Base case: all rivals assigned -> save permutation
        if (rivalIndex >= rivals.length) {
            targetDriver.addPermutation(new Permutation(targetDriver, simStandings));
            return;
        }

        Driver rival = rivals[rivalIndex];

        for (int rivalPos = 11; rivalPos >= 1; rivalPos--) {
            if (!posCheckValid(rivalPos, usedPositions)) continue;

            SimDriver rivalSim = new SimDriver(rival);
            rivalSim.setPosition(rivalPos);

            simStandings.add(rivalSim);
            usedPositions.add(rivalPos);

            // If this rival already beats the target, this branch is dead.
            if (!rivalBeatsTarget(mainSim, rivalSim)) {
                // Only continue assigning the next rival if target still survives
                assignRivalPositions(rivalIndex + 1, mainSim);
            }

            // Undo (so the loop can try the next rivalPos)
            simStandings.remove(simStandings.size() - 1);
            usedPositions.remove(usedPositions.size() - 1);
        }
    }

    private boolean rivalBeatsTarget(SimDriver targetSim, SimDriver rivalSim) {
        return rivalSim.getPoints() > targetSim.getPoints();
    }

    private boolean posCheckValid(int position, ArrayList<Integer> used) {
        if (position < 1 || position > 11) return false;
        return !used.contains(position);
    }

    private static Driver[] getRivals(Driver permutationDriver) {
        Driver[] contenders = Drivers2025.getContenders();
        ArrayList<Driver> rivals = new ArrayList<>();
        for (Driver d : contenders) {
            if (!d.getName().equals(permutationDriver.getName())) {
                rivals.add(d);
            }
        }
        return rivals.toArray(new Driver[0]);
    }
}