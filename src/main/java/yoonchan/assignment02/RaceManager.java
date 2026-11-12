package yoonchan.assignment02;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.text.Text;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Getter
public class RaceManager {
    private final ObjectProperty<RaceState> raceState;
    private final List<RacecarModel> cars;
    private final List<RacecarModel> podium;
    private static final List<Double[]> timePool = List.of(
            new Double[]{5d, 5.5},
            new Double[]{5.5, 7d},
            new Double[]{7d, 8.5},
            new Double[]{8.5, 10d});

    public RaceManager() {
        this.cars = new ArrayList<>();
        this.raceState = new SimpleObjectProperty<>(RaceState.READY);
        this.podium = new ArrayList<>();
    }

    // Standard JavaFX Property Accessors
    public RaceState getRaceState() {
        return raceState.get();
    }

    public void setRaceState(RaceState state) {
        this.raceState.set(state);
    }

    // Accessor for dependency injection in status call
    public ObjectProperty<RaceState> raceStateProperty() {
        return raceState;
    }

    /**
     * Initializer method that sets the race state to ready and initializes the podium and animation times.
     */
    public void initRace() {
        setRaceState(RaceState.READY);
        initPodium();
        initAnimationTimes();
    }

    /**
     * Helper method of initRace to initialize the podium.
     */
    private void initPodium() {
        Random rand = new Random();
        List<Integer> positions = new ArrayList<>(List.of(1, 2, 3, 4));

        podium.clear();
        podium.addAll(Collections.nCopies(cars.size(), null));

        for (RacecarModel car : cars) {
            int randomIndex = rand.nextInt(positions.size());
            int actualPosition = positions.remove(randomIndex);

            car.setFinishPosition(actualPosition);
            podium.set(actualPosition - 1, car);
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

    /**
     * Adds a car to the RaceManager's list of RacecarModels.
     *
     * @param car The RacecarModel to be added.
     */
    public void registerCarModel(RacecarModel car) {
        cars.add(car);
    }

    /**
     * Updates the podium's placement texts using the sorted podium list.
     *
     * @param placementTexts The Text nodes to be updated (ordered 1st to 4th).
     */
    public void updatePodiumPlacements(List<Text> placementTexts) {
        for (int i = 0; i < podium.size(); i++) {
            RacecarModel car = podium.get(i);
            if (car != null && i < placementTexts.size()) {
                placementTexts.get(i).setText(String.format("%s: #%d", car.getCarType(), car.getNumber()));
            }
        }
    }

    /**
     * Resets the podium's placement texts.
     *
     * @param placementTexts The Text nodes to be reset.
     */
    public void resetPodiumPlacements(List<Text> placementTexts) {
        for (Text placementText : placementTexts) {
            placementText.setText(" --- ");
        }
    }

    public enum RaceState {
        READY, LIVE, PAUSED, FINISHED
    }
}
