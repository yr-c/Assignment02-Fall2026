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
        Label lodgingFeesLabel = new Label("Lodging fees (per night):");
        Button calculateButtonLabel = new Button("Calculate expenses");

        Label[] leftLabels = {numDaysLabel, airfareLabel, carRentalLabel, milesDrivenLabel,
            parkingFeesLabel, taxiChargesLabel, registrationFeesLabel, lodgingFeesLabel};

        for (int i = 0; i < leftLabels.length; i++) {
            left.add(leftLabels[i], 0, i);
        }

        left.add(calculateButtonLabel, 0, leftLabels.length);

        TextField numDaysField = new TextField();
        TextField airfareField = new TextField();
        TextField carRentalField = new TextField();
        TextField milesDrivenField = new TextField();
        TextField parkingFeesField = new TextField();
        TextField taxiChargesField = new TextField();
        TextField registrationFeesField = new TextField();
        TextField lodgingFeesField = new TextField();

        TextField[] leftFields = {numDaysField, airfareField, carRentalField,
            milesDrivenField, parkingFeesField, taxiChargesField,
            registrationFeesField, lodgingFeesField};

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

        Label infoLabel = new Label("");

        right.getChildren().addAll(rightTitle, totalExpenses, allowableExpenses,
                expensesDue, expensesSaved, infoLabel);

        calculateButtonLabel.setOnAction(e -> {
            try {
                int numDays = Integer.parseInt(numDaysField.getText());
                double airfare = Double.parseDouble(airfareField.getText());
                double carRentalFees = Double.parseDouble(carRentalField.getText());
                double milesDriven = Double.parseDouble(milesDrivenField.getText());
                double parkingFees = Double.parseDouble(parkingFeesField.getText());
                double taxiFees = Double.parseDouble(taxiChargesField.getText());
                double registrationFees = Double.parseDouble(registrationFeesField.getText());
                double lodgingFees = Double.parseDouble(lodgingFeesField.getText());

                double[] receipt = calculateReciept(numDays, airfare,
                        carRentalFees, milesDriven, parkingFees, taxiFees,
                        registrationFees, lodgingFees);

                totalExpenses.setText(String.format("Total expenses: %.2f", receipt[0]));
                allowableExpenses.setText(String.format("Allowed expenses: %.2f", receipt[1]));
                expensesDue.setText(String.format("Expenses due: %.2f", receipt[2]));
                expensesSaved.setText(String.format("Expenses saved: %.2f", receipt[3]));

            } catch (NumberFormatException exception) {
                infoLabel.setText("Please input valid numbers for the fields.");
            }
        });

        Scene scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    /**
     * Calculates all trip expenses, and returns the receipt.
     *
     * @param numDays The number of airfare of the trip
     * @param airFare The airfare cost
     * @param carRentalFees The car rental cost
     * @param milesDriven The amount of miles driven on a private vehicle
     * @param parkingFees The parking cost
     * @param taxiFees The taxi cost
     * @param registrationFees The seminar or conference registration fees
     * @param lodgingFees The lodging fees
     * @return An array of double corresponding to: Total expenses,
     * reimbursement by company, expenses to to the client, amount saved by the
     * client.
     *
     */
    private static double[] calculateReciept(int numDays, double airfare,
            double carRentalFees, double milesDriven, double parkingFees, double taxiFees,
            double registrationFees, double lodgingFees) {

        double totalExpenses = airfare + carRentalFees + parkingFees + taxiFees
                + registrationFees + lodgingFees * numDays;

        double paidParkingPerDay = 10d;
        double paidTaxiFeesPerDay = 20d;
        double paidLodgingFeesPerDay = 95d;
        double paidPerMile = 0.27;

        double allowableExpenses = 37d * numDays + numDays * paidParkingPerDay
                + numDays * paidTaxiFeesPerDay + numDays * paidLodgingFeesPerDay
                + milesDriven * paidPerMile;

        double reimnbursement = 37d * numDays + Math.min(parkingFees, numDays * paidParkingPerDay)
                + Math.min(taxiFees, numDays * paidTaxiFeesPerDay)
                + Math.min(lodgingFees * numDays, numDays * paidLodgingFeesPerDay)
                + milesDriven * paidPerMile;

        double expensesDue = totalExpenses - reimnbursement;

        double amountSaved = allowableExpenses - totalExpenses;

        return new double[]{totalExpenses, allowableExpenses, expensesDue, amountSaved};
    }
}
