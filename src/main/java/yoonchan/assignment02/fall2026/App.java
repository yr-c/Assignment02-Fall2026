package yoonchan.assignment02.fall2026;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        HBox root = new HBox();
        GridPane left = new GridPane();
        VBox right = new VBox();
        root.getChildren().addAll(left, right);

        left.setAlignment(Pos.CENTER);
        left.setPadding(new Insets(10));
        Label numDays = new Label("Number of trip days:");
        Label airfare = new Label("Airfare ($):");
        Label carRental = new Label("Car rental:");
        Label milesDriven = new Label("Miles driven:");
        Label parkingFees = new Label("Parking Fees:");
        Label taxiCharges = new Label("Taxi charges:");
        Label registrationFees = new Label("Event registration fees:");
        Label lodgingCharges = new Label("Lodging charges (per night):");

        Label[] leftLabels = {numDays, airfare, carRental, milesDriven,
            parkingFees, taxiCharges, registrationFees, lodgingCharges};

        for (int i = 0; i < leftLabels.length; i++) {
            left.add(leftLabels[i], 0, i);
        }

        right.setAlignment(Pos.CENTER);
        TextField numDaysField = new TextField();
        TextField airFareField = new TextField();
        TextField carRentalField = new TextField();
        TextField milesDrivenField = new TextField();
        TextField parkingFeesField = new TextField();
        TextField taxiChargesField = new TextField();
        TextField registrationFeesField = new TextField();
        TextField lodgingChargesField = new TextField();

        TextField[] leftFields = {numDaysField, airFareField, carRentalField,
            milesDrivenField, parkingFeesField, taxiChargesField,
            registrationFeesField, lodgingChargesField};

        for (int i = 0; i < leftFields.length; i++) {
            left.add(leftFields[i], 1, i);
        }
        Scene scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
