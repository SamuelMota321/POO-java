import javax.swing.SwingUtilities;

import controllers.PurchaseController;
import view.PurchaseView;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PurchaseController controller = new PurchaseController();
            new PurchaseView(controller);
        });
    }
}
