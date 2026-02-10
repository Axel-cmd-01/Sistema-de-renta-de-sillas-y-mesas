import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class InventoryView {
    public static void showInventarioView(VBox contentArea) {
        contentArea.getChildren().clear();

        Label label = new Label("Aqui va el inventario de mesas y sillas");
    }
}
