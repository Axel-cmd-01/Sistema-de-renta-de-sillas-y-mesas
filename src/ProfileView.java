import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ProfileView {
    public static void showPerfilView(VBox contentArea, String user) {
        // Modificar todo

        contentArea.getChildren().clear();

        Label title = new Label("Mi Perfil");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 28));

        VBox perfilInfo = new VBox(10);
        perfilInfo.setAlignment(Pos.CENTER_LEFT);
        perfilInfo.setPadding(new Insets(20));
        perfilInfo.setStyle("-fx-background-color: white; -fx-border-color: #bdc3c7; -fx-border-radius: 5;");

        Label usuarioLabel = new Label("Usuario: " + user);
        Label emailLabel = new Label("Email: " + user + "@example.com");
        Label fechaLabel = new Label("Miembro desde: Octubre 2024");

        Button editarBtn = new Button("Editar Perfil");
        editarBtn.setStyle("-fx-font-size: 12; -fx-padding: 8 15;");
        editarBtn.setOnAction(e -> System.out.println("Editar perfil..."));

        perfilInfo.getChildren().addAll(usuarioLabel, emailLabel, fechaLabel, editarBtn);

        contentArea.getChildren().addAll(title, perfilInfo);
    }
}
