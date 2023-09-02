package Table;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class FXMLTRMController implements Initializable {
    // information input area
    @FXML
    private DatePicker txtdate;
    @FXML
    private TextField txtName;

    @FXML
    private TextArea txtNote;

    @FXML
    private ComboBox<String> arriveTime;
    @FXML
    private ComboBox<String> numberofPeople;
    @FXML
    private ComboBox<String> tableNumber;

    // buttons areas
    @FXML
    private Button backbtn;
    @FXML
    private Button nextbtn;
    @FXML
    private Button deletebtn;
    @FXML
    private Button readbtn;

    @FXML
    private Button updatebtn;

    @FXML
    private Button reservebtn;

    // display areas

    @FXML
    private Label countTable1;

    @FXML
    private Label countTable2;

    @FXML
    private Label countTable3;

    @FXML
    private Label countTable4;

    @FXML
    private Label countTable5;

    @FXML
    private Label countTable6;

    @FXML
    private Label dateTable1;

    @FXML
    private Label dateTable2;

    @FXML
    private Label dateTable3;

    @FXML
    private Label dateTable4;

    @FXML
    private Label dateTable5;

    @FXML
    private Label dateTable6;

    @FXML
    private Label nameTable1;

    @FXML
    private Label nameTable2;

    @FXML
    private Label nameTable3;

    @FXML
    private Label nameTable4;

    @FXML
    private Label nameTable5;

    @FXML
    private Label nameTable6;

    @FXML
    private Label notesTable1;

    @FXML
    private Label notesTable2;

    @FXML
    private Label notesTable3;

    @FXML
    private Label notesTable4;

    @FXML
    private Label notesTable5;

    @FXML
    private Label notesTable6;

    @FXML
    private Label seatsTable1;

    @FXML
    private Label seatsTable2;

    @FXML
    private Label seatsTable3;

    @FXML
    private Label seatsTable4;

    @FXML
    private Label seatsTable5;

    @FXML
    private Label seatsTable6;

    @FXML
    private BorderPane tableMain;

    @FXML
    private Label timeTable1;

    @FXML
    private Label timeTable2;

    @FXML
    private Label timeTable3;

    @FXML
    private Label timeTable4;

    @FXML
    private Label timeTable5;

    @FXML
    private Label timeTable6;

    private File input;
    private int counter = 0;
    int tableCount1 = 0;
    int tableCount2 = 0;
    int tableCount3 = 0;
    int tableCount4 = 0;
    int tableCount5 = 0;
    int tableCount6 = 0;

    String date;
    String name;
    String time;
    String tableNum;
    String people;
    String note = " ";

    Table table;
    ArrayList<Table> recordList = new ArrayList<Table>();

    public void addDataToArray() {
        date = txtdate.getValue().toString();
        name = txtName.getText();
        time = arriveTime.getValue().toString();
        tableNum = tableNumber.getValue().toString();
        people = numberofPeople.getValue().toString();
        note = txtNote.getText();

        Table table = new Table(date, name, tableNum, time, people, note);
        recordList.add(table);

    }

    public void addDataToFile() throws IOException {
        FileWriter output = new FileWriter("data.txt", false);
        PrintWriter writer = new PrintWriter(output);
        for (Table table : recordList) {
            writer.printf("%s,%s,%s,%s,%s,%s \n", table.getDate(), table.getName(), table.getTable(),
                    table.getTime(), table.getPeople(), table.getNote());
        }
        writer.close();
    }

    public void readFirstRecordFromFile() throws FileNotFoundException, IndexOutOfBoundsException {
        recordList.clear();
        input = new File("data.txt");
        String record;
        Scanner reader = new Scanner(input);
        counter = 0;
        reader.useDelimiter(",");
        Table table = null;
        while (reader.hasNext()) {
            record = reader.nextLine();
            String data[] = record.split(",");
            table = new Table(data[0], data[1], data[2], data[3], data[4], data[5]);
            recordList.add(table);
        }
    }

    public void setInputArea(int index) throws FileNotFoundException, IndexOutOfBoundsException {
        if (recordList.isEmpty()) {
            return;
        }
        LocalDate dateToSet = LocalDate.parse(recordList.get(index).getDate());
        txtdate.setValue(dateToSet);
        txtName.setText(recordList.get(index).getName());
        arriveTime.setValue(recordList.get(index).getTime());
        tableNumber.setValue(recordList.get(index).getTable());
        numberofPeople.setValue(recordList.get(index).getPeople());
        txtNote.setText(recordList.get(index).getNote());
    }

    @FXML
    void getDate(ActionEvent event) {
        // LocalDate myDate = txtdate.getValue();
        // System.out.println(myDate.toString());
    }

    @FXML
    void next(ActionEvent event) throws FileNotFoundException {
        if (counter >= recordList.size() - 1) {
            counter = recordList.size() - 1;
        } else {
            counter++;
        }
        setInputArea(counter);
    }

    @FXML
    void back(ActionEvent event) throws IndexOutOfBoundsException, FileNotFoundException {

        if (counter <= 0) {
            counter = 0;
        } else {
            counter--;
        }
        setInputArea(counter);
    }

    @FXML
    void read(ActionEvent event) throws FileNotFoundException {
        this.readFirstRecordFromFile();
        setInputArea(counter);
    }

    @FXML
    void delete(ActionEvent event) throws IndexOutOfBoundsException, IOException {
        if (recordList.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error: No reservation");
            alert.setContentText("No reservation");
            alert.showAndWait();
        } else {
            Table tempTable = recordList.get(counter);
            setDisplayToBlank(tempTable);
            recordList.remove(counter);
            if (input.exists()) {
                input.delete();
                this.addDataToFile();
                LocalDate dateToSet = LocalDate.parse(
                        "2023-01-01");
                txtdate.setValue(dateToSet);
                txtName.setText("");
                arriveTime.setValue("");
                tableNumber.setValue("");
                numberofPeople.setValue("");
                txtNote.setText("");
            }

        }
    }

    @FXML
    void updateHandle(ActionEvent event) throws IOException {
        Table tempTable = recordList.get(counter);
        setDisplayToBlank(tempTable);
        recordList.remove(counter);
        if (input.exists()) {
            input.delete();
        }
        this.addDataToFile();
        this.reserveHandle(event);
        read(event);

    }

    public boolean isAvailable() throws FileNotFoundException {
        for (Table table : recordList) {
            if (table.getTable().equals(tableNumber.getValue().toString())
                    && table.getDate().equals(txtdate.getValue().toString())) {

                return false;
            }
        }
        return true;
    }

    public boolean isSameTable() throws FileNotFoundException {
        for (Table table : recordList) {
            if (table.getTable().equals(tableNumber.getValue().toString())) {
                return true;
            }
        }
        return false;
    }

    public void showError(String titleTxt, String s) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(titleTxt);
        alert.setContentText(s);
        alert.showAndWait();
    }

    @FXML
    void reserveHandle(ActionEvent event) throws IOException {

        if (txtdate.getValue() == null || txtName.getText() == null || arriveTime.getValue() == null
                || tableNumber.getValue() == null || numberofPeople.getValue() == null) {
            // System.out.println("Fill the form");
            showError("Fill the form!", "Please fill all the information requaired!");

        } else if (isAvailable() == false)

        {
            System.out.println("The table is reserved");
            showError("Table is already reserved", "Table is already reserved");
        }

        else {
            if (isSameTable() == true) {
                addDataToArray();
                addDataToFile();
                addDisplay();

            } else {

                System.out.println("The table reserve sucessfully");
                addDataToArray();
                addDataToFile();
                setDisplay();
            }

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set combox
        arriveTime.getItems().removeAll();
        arriveTime.getItems().addAll("5:00 - 5:30", "5:30 - 6:00", "6:00 - 6:30", "6:30 - 7:00", "7:30 - 8:00");
        tableNumber.getItems().removeAll();
        tableNumber.getItems().addAll("1", "2", "3", "4", "5", "6");
        numberofPeople.getItems().removeAll();
        numberofPeople.getItems().addAll("1 - 2", "3 - 4", "5 - 6", "7 - 8", "9 - 10");
    }

    public void setDisplayToBlank(Table table) {
        if (table.getTable().equals("1")) {
            dateTable1.setText("");
            nameTable1.setText("");
            timeTable1.setText("");
            seatsTable1.setText("");
            notesTable1.setText("");

        } else if (table.getTable().equals("2")) {
            dateTable2.setText("");
            nameTable2.setText("");
            timeTable2.setText("");
            seatsTable2.setText("");
            notesTable2.setText("");

        } else if (table.getTable().equals("3")) {
            dateTable3.setText("");
            nameTable3.setText("");
            timeTable3.setText("");
            seatsTable3.setText("");
            notesTable3.setText("");

        } else if (table.getTable().equals("4")) {

            dateTable4.setText("");
            nameTable4.setText("");
            timeTable4.setText("");
            seatsTable4.setText("");
            notesTable4.setText("");
        } else if (table.getTable().equals("5")) {
            dateTable5.setText("");
            nameTable5.setText("");
            timeTable5.setText("");
            seatsTable5.setText("");
            notesTable5.setText("");
        } else {
            dateTable6.setText("");
            nameTable6.setText("");
            timeTable6.setText("");
            seatsTable6.setText("");
            notesTable6.setText("");
        }

    }

    public void setDisplay() {
        if (tableNumber.getValue().equals("1")) {
            dateTable1.setText(txtdate.getValue().toString());
            nameTable1.setText(txtName.getText());
            timeTable1.setText(arriveTime.getValue().toString());
            seatsTable1.setText(numberofPeople.getValue().toString());
            notesTable1.setText(txtNote.getText());

        } else if (tableNumber.getValue().equals("2")) {
            dateTable2.setText(txtdate.getValue().toString());
            nameTable2.setText(txtName.getText());
            timeTable2.setText(arriveTime.getValue().toString());
            seatsTable2.setText(numberofPeople.getValue().toString());
            notesTable2.setText(txtNote.getText());

        } else if (tableNumber.getValue().equals("3")) {
            dateTable3.setText(txtdate.getValue().toString());
            nameTable3.setText(txtName.getText());
            timeTable3.setText(arriveTime.getValue().toString());
            seatsTable3.setText(numberofPeople.getValue().toString());
            notesTable3.setText(txtNote.getText());

        } else if (tableNumber.getValue().equals("4")) {

            dateTable4.setText(txtdate.getValue().toString());
            nameTable4.setText(txtName.getText());
            timeTable4.setText(arriveTime.getValue().toString());
            seatsTable4.setText(numberofPeople.getValue().toString());
            notesTable4.setText(txtNote.getText());
        } else if (tableNumber.getValue().equals("5")) {
            dateTable5.setText(txtdate.getValue().toString());
            nameTable5.setText(txtName.getText());
            timeTable5.setText(arriveTime.getValue().toString());
            seatsTable5.setText(numberofPeople.getValue().toString());
            notesTable5.setText(txtNote.getText());
        } else {
            dateTable6.setText(txtdate.getValue().toString());
            nameTable6.setText(txtName.getText());
            timeTable6.setText(arriveTime.getValue().toString());
            seatsTable6.setText(numberofPeople.getValue().toString());
            notesTable6.setText(txtNote.getText());
        }

    }

    public void addDisplay() {
        if (tableNumber.getValue().equals("1")) {
            tableCount1++;
            if (tableCount1 > 0) {
                countTable1.setText(tableCount1 + "*" + "More reservations");
            }

        } else if (tableNumber.getValue().equals("2")) {
            tableCount2++;
            if (tableCount2 > 0) {
                countTable2.setText(tableCount2 + "*" + "More reservations");
            }
        } else if (tableNumber.getValue().equals("3")) {
            tableCount3++;
            if (tableCount3 > 0) {
                countTable3.setText(tableCount3 + "*" + "More reservations");
            }

        } else if (tableNumber.getValue().equals("4")) {
            tableCount4++;
            if (tableCount4 > 0) {
                countTable4.setText(tableCount4 + "*" + "More reservations");
            }
        } else if (tableNumber.getValue().equals("5")) {
            tableCount5++;
            if (tableCount5 > 0) {
                countTable5.setText(tableCount5 + "*" + "More reservations");
            }
        } else {
            tableCount6++;
            if (tableCount6 > 0) {
                countTable6.setText(tableCount6 + "*" + "More reservations");
            }
        }

    }
}
