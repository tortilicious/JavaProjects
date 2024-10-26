import model.Account;
import model.Purchase;
import ui.Menu;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Menu menu = new Menu(new Account(), new ArrayList<Purchase>());
        menu.run();
    }
}
