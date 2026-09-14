package yoonchan.assignment02.fall2026;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        Label numDaysLabel = new Label("Number of trip days:");
        Label airfareLabel = new Label("Airfare ($):");
        Label carRentalLabel = new Label("Car rental:");
        Label milesDrivenLabel = new Label("Miles driven:");
        Label parkingFeesLabel = new Label("Parking Fees:");
        Label taxiChargesLabel = new Label("Taxi charges:");
        Label registrationFeesLabel = new Label("Event registration fees:");
        Label lodgingChargesLabel = new Label("Lodging charges (per night):");
        Button calculateButtonLabel = new Button("Calculate expenses");

        Label[] leftLabels = {numDaysLabel, airfareLabel, carRentalLabel, milesDrivenLabel,
            parkingFeesLabel, taxiChargesLabel, registrationFeesLabel, lodgingChargesLabel};

        for (int i = 0; i < leftLabels.length; i++) {
            left.add(leftLabels[i], 0, i);
        }

        left.add(calculateButtonLabel, 0, leftLabels.length);

        calculateButtonLabel.setOnAction(e -> {
        });

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

        right.setAlignment(Pos.CENTER_LEFT);
        right.setPadding(new Insets(10));
        Label rightTitle = new Label("Summary:");
        Label totalExpenses = new Label("Total expenses: ");
        Label allowableExpenses = new Label("Allowable expenses: ");
        Label expensesDue = new Label("Expenses due: ");
        Label expensesSaved = new Label("Expenses saved: ");

        right.getChildren().addAll(rightTitle, totalExpenses, allowableExpenses,
                expensesDue, expensesSaved);

        Scene scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
