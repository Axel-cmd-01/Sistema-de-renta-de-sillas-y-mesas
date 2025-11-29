import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class InventarioView {
    public static void showInventarioView(VBox contentArea) {
        contentArea.getChildren().clear();

        Label titleLabel = new Label("Inventario");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 32));

        Region spacer = new Region();
        spacer.setPrefHeight(20);

        TableView<Inventario> tablaInventario = new TableView<>();
    }
}
