package yoonchan.assignment02;

import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RaceSceneController {
    private ParallelTransition pt = new ParallelTransition();

    // TODO: Set positions for transitions, animate wheels separately, create slideshow
    @FXML
    private void initialize() {
        RacecarModel redCar = new RacecarModel(12, "Ferrari", Color.RED);
        RacecarModel blueCar = new RacecarModel(43, "BMW", Color.BLUE);
        RacecarModel yellowCar = new RacecarModel(2, "Chevrolet", Color.YELLOW);
        RacecarModel greenCar = new RacecarModel(98, "Audi", Color.GREEN);

        RaceManager raceManager = new RaceManager(List.of(redCar, blueCar, yellowCar, greenCar));
        raceManager.initRace();

        TranslateTransition redCarTransition = new TranslateTransition(Duration.seconds(redCar.getAnimationTime()), getRedCar());
        TranslateTransition blueCarTransition = new TranslateTransition(Duration.seconds(blueCar.getAnimationTime()), getBlueCar());
        TranslateTransition yellowCarTransition = new TranslateTransition(Duration.seconds(yellowCar.getAnimationTime()), getYellowCar());
        TranslateTransition greenCarTransition = new TranslateTransition(Duration.seconds(greenCar.getAnimationTime()), getGreenCar());

        pt.getChildren().addAll(redCarTransition, blueCarTransition, yellowCarTransition, greenCarTransition);
    }

    @FXML
    private Group redCar;

    @FXML
    private Group blueCar;

    @FXML
    private Group yellowCar;

    @FXML
    private Group greenCar;

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
        pt.pause();
    }

    @FXML
    void resetRace(ActionEvent event) {
        pt.stop();
    }

    @FXML
    void startRace(ActionEvent event) {
        pt.play();
    }
}
