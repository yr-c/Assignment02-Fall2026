package yoonchan.assignment02;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.net.URL;

public class SoundEffectHelper {
    private MediaPlayer mediaPlayer;

    public SoundEffectHelper(String resourcePath) {
        URL resource = getClass().getResource(resourcePath);
        if (resource == null) {
            System.err.println("Sound resource not found: " + resourcePath);
        }

        Media sound = new Media(resource.toExternalForm());
        mediaPlayer = new MediaPlayer(sound);
    }

    /**
     * Plays the instance's audio from the start.
     */
    public void play() {
        if (mediaPlayer != null) {
            mediaPlayer.seek(Duration.ZERO);
            mediaPlayer.play();
        }
    }

    /**
     * Stops the instance's audio playback.
     */
    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
}
