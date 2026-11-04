package yoonchan.assignment02;

import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RaceManager {
    @Setter
    private RaceState raceState;
    private List<RacecarModel> cars;
    private RacecarModel[] podium;
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

        List<Integer> positions = new ArrayList<>(List.of(1, 2, 3, 4));

        for (RacecarModel car : cars) {
            int index = rand.nextInt(positions.size());

            car.setFinishPosition(index + 1);
            podium[index] = car;
            positions.remove(index);
        }
    }

    /**
     * Helper method of initRace to initialize the animation times.
     */
    private void initAnimationTimes() {
        Random rand = new Random();

        for (RacecarModel car : cars) {
            double lowerBound = timePool.get(car.getFinishPosition())[0];
            double upperBound = timePool.get(car.getFinishPosition())[1];

            car.setAnimationTime(rand.nextDouble() * (upperBound - lowerBound) + lowerBound);
        }
    }

    public enum RaceState {
        READY, LIVE, PAUSED, FINISHED
    }
}
