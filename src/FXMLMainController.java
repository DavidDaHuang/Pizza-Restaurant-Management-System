import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class FXMLMainController implements Initializable {

    @FXML
    private BorderPane borderpan2;

    @FXML
    private BorderPane boaderPane;

    @FXML
    private Button page1;

    @FXML
    private Button page2;

    @FXML
    void handlePage1(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu/Main.fxml"));
        borderpan2 = (BorderPane) loader.load();
        boaderPane.setCenter(borderpan2);

    }

    @FXML
    void handlePage2(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Table/FXMLTRM.fxml"));
        borderpan2 = (BorderPane) loader.load();
        boaderPane.setCenter(borderpan2);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu/Main.fxml"));
        try {
            borderpan2 = (BorderPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        boaderPane.setCenter(borderpan2);

    }

}
