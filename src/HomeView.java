import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Font;

public class HomeView {

    public static void showHomeView(String user, VBox contentArea) {
        contentArea.getChildren().clear();

        Label tittleLabel = new Label("Biienvenido " + user);
        tittleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 36));

        Label subTittleLabel = new Label("Sistema de Renta de Sillas y Mesas");
        subTittleLabel.setFont(Font.font("Arial", 18));
        subTittleLabel.setStyle("-fx-text-fill: #7f8c8d;");

        Label info = new Label("Selecciona una opción del menú lateral para comenzar");
        info.setFont(Font.font("Arial", 14));

        contentArea.getChildren().addAll(
                tittleLabel,
                subTittleLabel,
                info
        );
    }
}
