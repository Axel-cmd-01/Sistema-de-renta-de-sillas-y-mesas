import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class HomeView {

    public static void showHomeView(String user, VBox contentArea) {
        contentArea.getChildren().clear();
        contentArea.getStylesheets().clear();

        String css = HomeView.class.getResource("/styles/homeView.css").toExternalForm();
        contentArea.getStylesheets().add(css);

        Label titleLabel = new Label("Bienvenido " + user);
        titleLabel.getStyleClass().add("titleLabel");

        Label subTittleLabel = new Label("Sistema de Renta de Sillas y Mesas");
        subTittleLabel.getStyleClass().add("subTitleLabel");

        Label info = new Label("Selecciona una opción del menú lateral para comenzar");
        info.getStyleClass().add("infoLabel");

        contentArea.getChildren().addAll(
            titleLabel,
            subTittleLabel,
            info
        );
    }
}
