package yoonchan.assignment02;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.IOException;

public class SlideshowController {
    private static final Duration SLIDE_DURATION = Duration.seconds(3);

    private final SlideshowManager slideshowManager = new SlideshowManager();
    private final Image[] slideshowImages = slideshowManager.getSlideshowImages();
    private int slideshowIdx = 0;

    @FXML
    private ImageView slide;

    @FXML
    private void initialize() {
        if (slideshowImages.length == 0) {
            return;
        }

        // Show the first slide immediately at t = 0s
        slide.setImage(slideshowImages[slideshowIdx++]);
        playSlides();
    }

    private void playSlides() {
        Timeline slideshowTimeline = new Timeline(new KeyFrame(SLIDE_DURATION, event -> {
            if (slideshowIdx < slideshowImages.length) {
                slide.setImage(slideshowImages[slideshowIdx++]);
            }
        }));

        slideshowTimeline.setCycleCount(slideshowImages.length);
        slideshowTimeline.setOnFinished(event -> transitionToRaceScene());
        slideshowTimeline.play();
    }

    private void transitionToRaceScene() {
        try {
            App.setRoot("RaceScene");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load RaceScene.fxml", e);
        }
    }
}