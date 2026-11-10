package yoonchan.assignment02;

import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RaceSceneController {
    private static final double FINISH_LINE_X = 828.0;
    private static final int TIRE_ROTATIONS = 5;

    private final ParallelTransition pt = new ParallelTransition();

    @FXML
    private Group redCar, blueCar, yellowCar, greenCar;
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
    private void initialize() {
        RacecarModel redModel = new RacecarModel(12, "Ferrari", Color.RED);
        RacecarModel blueModel = new RacecarModel(43, "BMW", Color.BLUE);
        RacecarModel yellowModel = new RacecarModel(2, "Chevrolet", Color.YELLOW);
        RacecarModel greenModel = new RacecarModel(98, "Audi", Color.GREEN);

        RaceManager raceManager = new RaceManager(List.of(redModel, blueModel, yellowModel, greenModel));
        raceManager.initRace();

        registerCarAnimation(redModel, redCar, redCarFrontTire, redCarBackTire);
        registerCarAnimation(blueModel, blueCar, blueCarFrontTire, blueCarBackTire);
        registerCarAnimation(yellowModel, yellowCar, yellowCarFrontTire, yellowCarBackTire);
        registerCarAnimation(greenModel, greenCar, greenCarFrontTire, greenCarBackTire);

        speedSlider.valueProperty().addListener((ov, oldVal, newVal) -> pt.setRate(newVal.doubleValue()));
    }

    private void registerCarAnimation(RacecarModel model, Group carGroup, Node frontTire, Node backTire) {
        Duration duration = Duration.seconds(model.getAnimationTime());

        TranslateTransition move = new TranslateTransition(duration, carGroup);
        move.setToX(FINISH_LINE_X);

        RotateTransition frontSpin = createTireSpin(duration, frontTire);
        RotateTransition backSpin = createTireSpin(duration, backTire);

        pt.getChildren().addAll(move, frontSpin, backSpin);
    }

    private RotateTransition createTireSpin(Duration totalDuration, Node tire) {
        RotateTransition spin = new RotateTransition(totalDuration.divide(TIRE_ROTATIONS), tire);
        spin.setByAngle(360);
        spin.setCycleCount(TIRE_ROTATIONS);
        spin.setInterpolator(Interpolator.LINEAR);
        return spin;
    }

    @FXML
    void pauseRace(ActionEvent event) {
        pt.pause();
    }

    @FXML
    void resetRace(ActionEvent event) {
        pt.stop();

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
    }

    @FXML
    void startRace(ActionEvent event) {
        pt.play();
    }
}
