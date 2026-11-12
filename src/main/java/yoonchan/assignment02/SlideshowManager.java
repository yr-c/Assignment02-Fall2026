package yoonchan.assignment02;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

import java.util.Objects;

public class SlideshowManager {
    private static final int SLIDE_COUNT = 5;
    private static final String SPRITESHEET_PATH = "images/Slideshow_cars.png";

    public Image[] getSlideshowImages() {
        String url = Objects.requireNonNull(
                getClass().getResource(SPRITESHEET_PATH),
                "Spritesheet not found at " + SPRITESHEET_PATH
        ).toExternalForm();

        Image spritesheet = new Image(url);
        PixelReader reader = spritesheet.getPixelReader();

        int width = (int) spritesheet.getWidth();
        int sliceHeight = (int) spritesheet.getHeight() / SLIDE_COUNT;

        Image[] slides = new Image[SLIDE_COUNT];
        for (int i = 0; i < SLIDE_COUNT; i++) {
            slides[i] = new WritableImage(reader, 0, i * sliceHeight, width, sliceHeight);
        }

        return slides;
    }
}
