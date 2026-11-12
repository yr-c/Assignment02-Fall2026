package yoonchan.assignment02;

import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RaceSceneController {
    private final RaceManager rm = new RaceManager();
    private static final double FINISH_LINE_X = 828.0;
    private static final int TIRE_ROTATIONS = 5;

    private final ParallelTransition pt = new ParallelTransition();

    @FXML
    private Group redCar, blueCar, yellowCar, greenCar;
    @FXML
    private Label statusText;
    @FXML
    private Line redCarFrontTire, redCarBackTire;
    @FXML
    private Line blueCarFrontTire, blueCarBackTire;
    @FXML
    private Line yellowCarFrontTire, yellowCarBackTire;
    @FXML
    private Line greenCarFrontTire, greenCarBackTire;
    @FXML
    private Button startButton, pauseButton, resetButton;
    @FXML
    private Slider speedSlider;
    @FXML
    private Text firstPlaceText, secondPlaceText, thirdPlaceText, fourthPlaceText;

    @FXML
    private void initialize() {
        RacecarModel redModel = new RacecarModel(12, "Ferrari", Color.RED);
        RacecarModel blueModel = new RacecarModel(43, "BMW", Color.BLUE);
        RacecarModel yellowModel = new RacecarModel(2, "Chevrolet", Color.YELLOW);
        RacecarModel greenModel = new RacecarModel(98, "Audi", Color.GREEN);

        rm.registerCarModel(redModel);
        rm.registerCarModel(blueModel);
        rm.registerCarModel(yellowModel);
        rm.registerCarModel(greenModel);
        rm.initRace();

        registerCarAnimation(redModel, redCar, redCarFrontTire, redCarBackTire);
        registerCarAnimation(blueModel, blueCar, blueCarFrontTire, blueCarBackTire);
        registerCarAnimation(yellowModel, yellowCar, yellowCarFrontTire, yellowCarBackTire);
        registerCarAnimation(greenModel, greenCar, greenCarFrontTire, greenCarBackTire);
        pt.setCycleCount(1);

        speedSlider.valueProperty().addListener((ov, oldVal, newVal) -> pt.setRate(newVal.doubleValue()));

        statusText.textProperty().bind(Bindings.createStringBinding(
                () -> "The race is " + rm.getRaceState().toString() + "!", rm.raceStateProperty())
        );

        pt.setOnFinished(e -> {
            rm.setRaceState(RaceManager.RaceState.FINISHED);
            rm.updatePodiumPlacements(List.of(firstPlaceText, secondPlaceText, thirdPlaceText, fourthPlaceText));
        });
    }

    /**
     * Registers a car model to the animation engine.
     *
     * @param model     The model to be registered.
     * @param carGroup  The FXML group of the model in the scene.
     * @param frontTire The front tire line of the FXML model.
     * @param backTire  The back tire line of the FXML model.
     */
    private void registerCarAnimation(RacecarModel model, Group carGroup, Node frontTire, Node backTire) {
        Duration duration = Duration.seconds(model.getAnimationTime());

        TranslateTransition move = new TranslateTransition(duration, carGroup);
        move.setToX(FINISH_LINE_X);

        RotateTransition frontSpin = createTireSpin(duration, frontTire);
        RotateTransition backSpin = createTireSpin(duration, backTire);

        pt.getChildren().addAll(move, frontSpin, backSpin);
    }

    /**
     * Creates a tire spin animation for an input tire line.
     *
     * @param totalDuration The total duration of the spin.
     * @param tireLine      The FXML tire line.
     * @return The rotation transition of the tire line.
     */
    private RotateTransition createTireSpin(Duration totalDuration, Node tireLine) {
        RotateTransition spin = new RotateTransition(totalDuration.divide(TIRE_ROTATIONS), tireLine);
        spin.setByAngle(360);
        spin.setCycleCount(TIRE_ROTATIONS);
        spin.setInterpolator(Interpolator.LINEAR);
        return spin;
    }

    /**
     * Pauses the race and sets the RaceManager's Racestate to "PAUSED".
     */
    @FXML
    void pauseRace() {
        pt.pause();
        startButton.setText("Continue");
        rm.setRaceState(RaceManager.RaceState.PAUSED);
    }

    /**
     * Resets the race and  the RaceManager's Racestate to "READY".
     */
    @FXML
    void resetRace() {
        pt.stop();
        startButton.setText("Start");

        for (Group car : List.of(redCar, blueCar, yellowCar, greenCar)) {
            car.setTranslateX(0);
        }
        for (Line tire : List.of(
                redCarFrontTire, redCarBackTire,
                blueCarFrontTire, blueCarBackTire,
                yellowCarFrontTire, yellowCarBackTire,
                greenCarFrontTire, greenCarBackTire)) {
            tire.setRotate(0);
        }

        rm.setRaceState(RaceManager.RaceState.READY);
        rm.resetPodiumPlacements(List.of(firstPlaceText, secondPlaceText, thirdPlaceText, fourthPlaceText));
    }

    /**
     * Starts the race and  the RaceManager's Racestate to "LIVE".
     */
    @FXML
    void startRace() {
        pt.play();
        startButton.setText("Start");
        rm.setRaceState(RaceManager.RaceState.LIVE);
    }
}
