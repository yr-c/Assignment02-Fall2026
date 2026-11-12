package yoonchan.assignment02;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
public class RaceManager {
    @Setter
    private RaceState raceState;
    private final List<RacecarModel> cars;
    private final RacecarModel[] podium;
    private static final List<Double[]> timePool = List.of(
            new Double[]{5d, 5.5},
            new Double[]{5.5, 7d},
            new Double[]{7d, 8.5},
            new Double[]{8.5, 10d});

    public RaceManager(List<RacecarModel> cars) {
        this.cars = cars;
        raceState = RaceState.READY;
        podium = new RacecarModel[cars.size()];
    }

    /**
     * Initializer method that sets the race state to ready and initializes the podium and animation times.
     */
    public void initRace() {
        raceState = RaceState.READY;
        initPodium();
        initAnimationTimes();
    }

    /**
     * Helper method of initRace to initialize the podium.
     */
    private void initPodium() {
        Random rand = new Random();

        // Available finishing positions
        List<Integer> positions = new ArrayList<>(List.of(1, 2, 3, 4));

        for (RacecarModel car : cars) {
            // 1. Get a random INDEX from the remaining available positions
            int randomIndex = rand.nextInt(positions.size());

            // 2. Remove the position from the list so it can't be reused
            // (remove(int index) returns the value that was at that index)
            int actualPosition = positions.remove(randomIndex);

            // 3. Assign the actual position to the car
            car.setFinishPosition(actualPosition);
            podium[actualPosition - 1] = car;
        }
    }

    /**
     * Helper method of initRace to initialize the animation times.
     */
    private void initAnimationTimes() {
        Random rand = new Random();

        for (RacecarModel car : cars) {
            double lowerBound = timePool.get(car.getFinishPosition() - 1)[0];
            double upperBound = timePool.get(car.getFinishPosition() - 1)[1];

            car.setAnimationTime(rand.nextDouble() * (upperBound - lowerBound) + lowerBound);
        }
    }

    public enum RaceState {
        READY, LIVE, PAUSED, FINISHED
    }
}
