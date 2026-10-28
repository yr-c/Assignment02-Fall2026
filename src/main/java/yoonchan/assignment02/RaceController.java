package yoonchan.assignment02;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

public class RaceController {
    private static RaceState raceState;
    private static RacecarModel winner;

    public static RaceState getRaceState() {
        return raceState;
    }

    public static void setRaceState(RaceState raceState) {
        RaceController.raceState = raceState;
    }

    public enum RaceState {
        READY, LIVE, PAUSED, FINISHED
    }

    @FXML
    private void initialize() {

    }

    @FXML
    private Group car1;

    @FXML
    private Group car2;

    @FXML
    private Group car3;

    @FXML
    private Group car4;

    @FXML
    private Button pauseButton;

    @FXML
    private Button resetButton;

    @FXML
    private Slider speedSlider;

    @FXML
    private Button startButton;

    @FXML
    void pauseRace(ActionEvent event) {

    }

    @FXML
    void resetRace(ActionEvent event) {

    }

    @FXML
    void startRace(ActionEvent event) {

    }
}
