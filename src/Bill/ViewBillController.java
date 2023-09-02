package Bill;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class ViewBillController {
    @FXML
    private TextArea billTextArea;

    public void setBillText(String billText) {
        billTextArea.setText(billText);
    }

    @FXML
    public void handleClearBillButton() {
        BillData.orderedItems.clear();
        BillData.totalBill = 0.0;
        billTextArea.setText("Items Ordered: ");
    }
}
