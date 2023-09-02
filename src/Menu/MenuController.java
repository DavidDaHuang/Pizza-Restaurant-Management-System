package Menu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import Bill.BillData;
import Bill.ViewBillController;

public class MenuController {
    @FXML
    private Button pizzaButton;
    @FXML
    private Button sidesButton;
    @FXML
    private Button dessertsButton;
    @FXML
    private Button drinksButton;
    @FXML
    private Button viewBillButton;
    @FXML
    private TextArea billTextArea;

    //
    @FXML
    private BorderPane menuDisplay;

    @FXML
    private BorderPane menuMain;

    //

    @FXML
    public void handlePizzaButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Pizza.fxml"));
        menuDisplay = (BorderPane) loader.load();
        menuMain.setCenter(menuDisplay);

    }

    @FXML
    public void handleSidesButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Side.fxml"));
        menuDisplay = (BorderPane) loader.load();
        menuMain.setCenter(menuDisplay);

    }

    @FXML
    public void handleDessertsButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Dessert.fxml"));
        menuDisplay = (BorderPane) loader.load();
        menuMain.setCenter(menuDisplay);

    }

    @FXML
    public void handleDrinksButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Drink.fxml"));
        menuDisplay = (BorderPane) loader.load();
        menuMain.setCenter(menuDisplay);

    }

    @FXML
    public void handleViewBillButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Bill/ViewBill.fxml"));
            Parent root = loader.load();
            ViewBillController controller = loader.getController();

            StringBuilder billText = new StringBuilder("Items Ordered:\n");
            for (String item : BillData.orderedItems) {
                billText.append(item).append("\n");
            }
            billText.append("Total Bill: $").append(BillData.totalBill);
            controller.setBillText(billText.toString());

            Stage stage = new Stage();
            stage.setTitle("View Bill");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handlePepperoniPizzaButton() {
        addToBill("Pepperoni Pizza", 10.0);
    }

    @FXML
    public void handleCheesePizzaButton() {
        addToBill("Cheese Pizza", 10.0);
    }

    @FXML
    public void handleMeatPizzaButton() {
        addToBill("Meat Pizza", 15.0);
    }

    @FXML
    public void handleVegePizzaButton() {
        addToBill("Vege Pizza", 15.0);
    }

    @FXML
    public void handleFrenchFriesButton() {
        addToBill("French Fries", 5.0);
    }

    @FXML
    public void handleGarlicBreadButton() {
        addToBill("Garlic Bread", 5.0);
    }

    @FXML
    public void handleIceCreamButton() {
        addToBill("Ice Cream", 2.0);
    }

    @FXML
    public void handleCakeButton() {
        addToBill("Cake", 2.0);
    }

    @FXML
    public void handleSoftDrinkButton() {
        addToBill("Soft Drink", 1.5);
    }

    @FXML
    public void handleCoffeeButton() {
        addToBill("Coffee", 1.5);
    }

    @FXML
    public void handleTeaButton() {
        addToBill("Tea", 1.5);
    }

    private void addToBill(String itemName, double itemPrice) {
        MenuItem item = new MenuItem(itemName, itemPrice);
        BillData.addToBill(item);

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Item Added");
        alert.setHeaderText(null);
        alert.setContentText(itemName + " has been added to your bill.");
        alert.showAndWait();
    }
}
