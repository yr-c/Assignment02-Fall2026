package yoonchan.assignment02;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RaceController {
    @FXML
    private void initialize() {
        RacecarModel car1 = new RacecarModel(12, "Ferrari", Color.RED);
        RacecarModel car2 = new RacecarModel(43, "BMW", Color.BLUE);
        RacecarModel car3 = new RacecarModel(2, "Chevrolet", Color.YELLOW);
        RacecarModel car4 = new RacecarModel(98, "Audi", Color.GREEN);

        RaceManager raceManager = new RaceManager(List.of(car1, car2, car3, car4));
        raceManager.initRace();

        getCar1().setId("redCar");
        getCar2().setId("blueCar");
        getCar3().setId("yellowCar");
        getCar4().setId("greenCar");
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
