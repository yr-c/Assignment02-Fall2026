package yoonchan.assignment02;

import javafx.scene.paint.Color;

public class RacecarModel {
    private CarType carType;
    private Color color;
    private double[] startPosition;
    private double speed;

    public RacecarModel(CarType carType, Color color, double[] startPosition, double speed) {
        this.carType = carType;
        this.color = color;
        this.startPosition = startPosition;
        this.speed = speed;
    }

    public enum CarType {
        SEDAN, SUV, BUS
    }
}
