package yoonchan.assignment02;

import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;

@Getter
public class RacecarModel {
    private final int number;
    private final String carType;
    private final Color color;
    @Setter private int finishPosition;
    @Setter private double animationTime;

    public RacecarModel(int number, String carType, Color color) {
        this.number = number;
        this.carType = carType;
        this.color = color;
    }

}
