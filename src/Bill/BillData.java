package Bill;

import java.util.ArrayList;
import Menu.MenuItem;

public class BillData {
    public static ArrayList<String> orderedItems = new ArrayList<>();
    public static double totalBill = 0.0;

    public static void addToBill(MenuItem menuItem) {
        totalBill += menuItem.getPrice();
        orderedItems.add(menuItem.getName());
    }
}
